package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SysApi extends BaseDeiEntity {
    private String code;
    private String name;
    private String type;
    private String memo;
    private String url;
    private String httpMethod;
    private String sysPrjModuleName;
    private String sysPrjModuleCode;
    private Long sysPrjSid;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysApi that = (SysApi) o;
        if (Objects.nonNull(getSid())
                && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            if (StringUtils.isNotBlank(code)
                    && StringUtils.isNotBlank(that.code)) {
                return StringUtils.equals(code, that.code)
                        && Objects.equals(sysPrjSid, that.sysPrjSid);
            } else {
                return StringUtils.equals(url, that.url)
                        && StringUtils.equals(httpMethod, that.httpMethod)
                        && Objects.equals(sysPrjSid, that.sysPrjSid);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, url, httpMethod, sysPrjSid);
    }

    @Getter
    @AllArgsConstructor
    public enum Type implements BaseDeiEnum {
        AUTH_NEEDED("auth-needed", "需授权的接口"),
        AUTH_IGNORED("auth-ignored", "忽略授权的接口 (需要access token才能访问, 不需要授权的接口)"),
        PUBLIC("public", "公共接口 (不需要access token就可以访问的接口)"),
        ;

        private final String code;
        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "系统接口编号"),
        NAME("name", "系统接口名称"),
        TYPE("type", "系统接口类型"),
        MEMO("memo", "系统接口备注"),
        URL("url", "系统接口访问地址"),
        HTTP_METHOD("httpMethod", "系统接口的HTTP Method"),
        SYS_PRJ_MODULE_NAME("sysPrjModuleName", "系统接口所属模块名称"),
        SYS_PRJ_MODULE_CODE("sysPrjModuleCode", "系统接口所属模块编号"),
        SYS_PRJ_SID("sysPrjSid", "系统接口所属项目主键ID"),
        STATUS("status", "系统接口状态"),
        ;

        private final String code;
        private final String desc;
    }
}