package com.ioiox.dei.duc.springboot.rest.api;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.spring.core.model.DUCUserPortfolio;
import com.ioiox.dei.duc.springboot.helper.DUCJwtAuthCtxHelper;
import com.ioiox.dei.duc.springboot.jwt.JwtCoreSvc;
import com.ioiox.dei.duc.springboot.rest.vo.ChgPwdReq;
import com.ioiox.dei.duc.springboot.rest.vo.LoginReq;
import com.ioiox.dei.duc.springboot.rest.vo.RefreshAccessTokenReq;
import com.ioiox.dei.duc.springboot.rest.vo.ResetPwdReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class DUCCoreRestApi {

    private final JwtCoreSvc jwtCoreSvc;

    public DUCCoreRestApi(final JwtCoreSvc jwtCoreSvc) {
        this.jwtCoreSvc = jwtCoreSvc;
    }

    @RequestMapping(value = "${com.ioiox.dei.duc.sys.get-user-portfolio-url:/api/users/self/userPortfolio}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DeiResponseData<DUCUserPortfolio>> getUserPortfolio() {
        return ResponseEntity.status(HttpStatus.OK).body(new DeiResponseData.Builder<DUCUserPortfolio>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(DUCJwtAuthCtxHelper.getUserPortfolio())
                .build());
    }

    @RequestMapping(path = "${com.ioiox.dei.duc.sys.login-url:/api/{userType}/login}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeiResponseData<Map<String, String>>> login(@RequestBody LoginReq loginReq,
                                                                      @PathVariable("userType") final String userType) {
        return ResponseEntity.status(HttpStatus.OK).body(new DeiResponseData.Builder<Map<String, String>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(jwtCoreSvc.login(loginReq, userType))
                .build());
    }

    @RequestMapping(path = "${com.ioiox.dei.duc.sys.reset-pwd-url:/api/{userType}/pwd/reset}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeiResponseData<String>> resetPwd(@RequestBody final ResetPwdReq resetPwdReq, @PathVariable("userType") final String userType) {
        jwtCoreSvc.resetPwd(resetPwdReq, userType);
        return ResponseEntity.status(HttpStatus.OK).body(new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("重置密码成功")
                .build());
    }

    @RequestMapping(path = "${com.ioiox.dei.duc.sys.chg-pwd-url:/api/self/pwd/chg}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeiResponseData<String>> chgPwd(@RequestBody final ChgPwdReq chgPwdReq) {
        jwtCoreSvc.chgPwd(chgPwdReq);
        return ResponseEntity.status(HttpStatus.OK).body(new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("修改密码成功")
                .build());
    }

    @RequestMapping(value="${com.ioiox.dei.duc.sys.renew-access-token-url:}", method= RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DeiResponseData<Map<String, String>>>  renewAccessToken(@RequestBody RefreshAccessTokenReq refreshAccessTokenReq) {
        return ResponseEntity.status(HttpStatus.OK).body(new DeiResponseData.Builder<Map<String, String>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(jwtCoreSvc.renewAccessToken(refreshAccessTokenReq))
                .build());
    }
}
