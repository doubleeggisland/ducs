package com.ioiox.dei.rest.api.clients;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.support.TraceIdCtxHelper;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.rest.api.clients.exception.DeiRestApiInvokeException;
import com.ioiox.dei.rest.api.clients.exception.DeiRestApiUnavailableException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public abstract class BaseDeiRestApiClient {

    protected static final Logger log = LoggerFactory.getLogger(BaseDeiRestApiClient.class);

    private final RestTemplate restTemplate;
    private final String serverHost;

    public BaseDeiRestApiClient(final RestTemplate restTemplate,
                                final String serverHost) {
        this.restTemplate = restTemplate;
        this.serverHost = serverHost;
    }

    protected HttpHeaders buildCommonHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(TraceIdCtxHelper.HEADER_KEY_TRACE_ID, TraceIdCtxHelper.getTraceId());
        return headers;
    }

    protected <I> ResponseEntity<I> execute(final String restApiUrl,
                                            final HttpMethod httpMethod,
                                            final HttpEntity<?> httpEntity,
                                            final ParameterizedTypeReference<I> respType,
                                            boolean printInvokeInfo) {
        return execute(restApiUrl, httpMethod, null, httpEntity, respType, printInvokeInfo);
    }

    protected <T> ResponseEntity<T> execute(final String restApiUrl,
                                            final HttpMethod httpMethod,
                                            final Map<String, ?> queryParams,
                                            HttpEntity<?> httpEntity,
                                            final ParameterizedTypeReference<T> respType,
                                            boolean printInvokeInfo) {
        ResponseEntity<T> respEntity;
        final long startTime = System.currentTimeMillis();
        final ReqCtx.Builder reqCtxBuilder = new ReqCtx.Builder();
        reqCtxBuilder
                .restApiUrl(restApiUrl)
                .httpMethod(httpMethod.name())
                .requestHeaders(Objects.isNull(httpEntity) ? DeiGlobalConstant.ZERO_LENGTH_STR : httpEntity.getHeaders().toString())
                .requestParams(DeiCollectionUtil.isEmpty(queryParams) ? DeiGlobalConstant.ZERO_LENGTH_STR : JsonUtil.toJsonStr(queryParams))
                .requestBody(Objects.isNull(httpEntity) || Objects.isNull(httpEntity.getBody()) ? DeiGlobalConstant.ZERO_LENGTH_STR : JsonUtil.toJsonStr(httpEntity.getBody()))
                .originalReq(getOriginalReq());

        try {
            if (Objects.isNull(httpEntity)) {
                final HttpHeaders headers = new HttpHeaders();
                headers.add(TraceIdCtxHelper.HEADER_KEY_TRACE_ID, TraceIdCtxHelper.getTraceId());
                httpEntity = new HttpEntity<>(headers);
            }
            respEntity = restTemplate.exchange(restApiUrl, httpMethod, httpEntity, respType);
            reqCtxBuilder
                    .respStatus(respEntity.getStatusCodeValue())
                    .respBody(JsonUtil.toJsonStr(respEntity))
                    .respHeaders(respEntity.getHeaders().toString());
        } catch (RestClientException e) {
            reqCtxBuilder
                    .errCause(e);
            throw new DeiRestApiUnavailableException(e.getMessage(), e);
        } finally {
            reqCtxBuilder
                    .elapsedTime(System.currentTimeMillis() - startTime);
            if (log.isInfoEnabled()
                    && printInvokeInfo) {
                log.info(reqCtxBuilder.build().invokeInfo());
            }
        }
        return respEntity;
    }

    protected <T> DeiResponseData<T> checkResponse(final ResponseEntity<DeiResponseData<T>> respEntity) {
        final HttpStatus.Series series = HttpStatus.Series.valueOf(respEntity.getStatusCode());
        if (HttpStatus.Series.CLIENT_ERROR == series || HttpStatus.Series.SERVER_ERROR == series) {
            if (Objects.nonNull(respEntity.getBody())) {
                throw new DeiRestApiInvokeException(respEntity.getBody().getMsg(), respEntity.getBody().getCode(), respEntity.getBody().getTraceId());
            } else {
                throw new DeiRestApiInvokeException(respEntity.getStatusCode().getReasonPhrase());
            }
        }
        if (Objects.isNull(respEntity.getBody())) {
            throw new DeiRestApiInvokeException("unexpected server error!");
        }
        if (DeiResponseData.SUCCESS != respEntity.getBody().getCode()) {
            throw new DeiRestApiInvokeException(respEntity.getBody().getMsg(), respEntity.getBody().getCode(), respEntity.getBody().getTraceId());
        }
        return respEntity.getBody();
    }

    public String getServerHost() {
        return serverHost;
    }

    private OriginalRequest getOriginalReq() {
        try {
            final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (Objects.isNull(requestAttributes)) {
                return null;
            }
            final HttpServletRequest httpReq = ((ServletRequestAttributes) requestAttributes).getRequest();
            return new OriginalRequest(httpReq.getRequestURL().toString(), getClientIp(httpReq));
        } catch (Exception e) {
            return null;
        }
    }

    private String getClientIp(final HttpServletRequest request) {
        if (Objects.isNull(request)) {
            return null;
        }
        String clientIp = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(clientIp)) {
            clientIp = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class ReqCtx {
        private String restApiUrl;
        private String httpMethod;
        private String requestHeaders;
        private String requestParams;
        private String requestBody;
        private int respStatus;
        private String respHeaders;
        private String respBody;
        private long elapsedTime;
        private OriginalRequest originalReq;
        private Throwable errCause;

        private ReqCtx(final Builder builder) {
            restApiUrl = builder.restApiUrl;
            httpMethod = builder.httpMethod;
            requestHeaders = builder.requestHeaders;
            requestParams = builder.requestParams;
            requestBody = builder.requestBody;
            respStatus = builder.respStatus;
            respHeaders = builder.respHeaders;
            respBody = builder.respBody;
            elapsedTime = builder.elapsedTime;
            originalReq = builder.originalReq;
            errCause = builder.errCause;
        }

        public String invokeInfo() {
            return String.format("clientIp: %s, originReqUrl: %s, restApiUrl: %s, httpMethod:%s, requestHeaders:%s, requestParams: %s, payload:%s, reqTimeCost: %sms, respStatus:%s, respHeaders: %s, respBody:%s %s",
                    Objects.isNull(originalReq) || StringUtils.isBlank(originalReq.getClientIp()) ? DeiGlobalConstant.ZERO_LENGTH_STR : originalReq.getClientIp(),
                    Objects.isNull(originalReq) || StringUtils.isBlank(originalReq.getUrl()) ? DeiGlobalConstant.ZERO_LENGTH_STR : originalReq.getUrl(),
                    StringUtils.isBlank(this.restApiUrl) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.restApiUrl,
                    StringUtils.isBlank(this.httpMethod) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.httpMethod,
                    StringUtils.isBlank(this.requestHeaders) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.requestHeaders,
                    StringUtils.isBlank(this.requestParams) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.requestParams,
                    StringUtils.isBlank(this.requestBody) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.requestBody,
                    this.elapsedTime,
                    this.respStatus,
                    StringUtils.isBlank(this.respHeaders) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.respHeaders,
                    StringUtils.isBlank(this.respBody) ? DeiGlobalConstant.ZERO_LENGTH_STR : this.respBody,
                    Objects.nonNull(errCause) ? String.format("errMsg: %s", errCause.getMessage()) : DeiGlobalConstant.ZERO_LENGTH_STR);
        }

        @NoArgsConstructor
        public static class Builder {
            private String restApiUrl;
            private String httpMethod;
            private String requestHeaders;
            private String requestParams;
            private String requestBody;
            private int respStatus;
            private String respHeaders;
            private String respBody;
            private long elapsedTime;
            private OriginalRequest originalReq;
            private Throwable errCause;

            public Builder restApiUrl(final String restApiUrl) {
                this.restApiUrl = restApiUrl;
                return this;
            }
            public Builder httpMethod(final String httpMethod) {
                this.httpMethod = httpMethod;
                return this;
            }
            public Builder requestHeaders(final String requestHeaders) {
                this.requestHeaders = requestHeaders;
                return this;
            }
            public Builder requestParams(final String requestParams) {
                this.requestParams = requestParams;
                return this;
            }
            public Builder requestBody(final String requestBody) {
                this.requestBody = requestBody;
                return this;
            }
            public Builder respStatus(final int respStatus) {
                this.respStatus = respStatus;
                return this;
            }
            public Builder respHeaders(final String respHeaders) {
                this.respHeaders = respHeaders;
                return this;
            }
            public Builder respBody(final String respBody) {
                this.respBody = respBody;
                return this;
            }
            public Builder elapsedTime(final long elapsedTime) {
                this.elapsedTime = elapsedTime;
                return this;
            }
            public Builder originalReq(final OriginalRequest originalReq) {
                this.originalReq = originalReq;
                return this;
            }
            public Builder errCause(final Throwable errCause) {
                this.errCause = errCause;
                return this;
            }

            public ReqCtx build() {
                return new ReqCtx(this);
            }
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class OriginalRequest {
        private String url;
        private String clientIp;
        public OriginalRequest(final String url, final String clientIp) {
            this.url = url;
            this.clientIp = clientIp;
        }
    }
}
