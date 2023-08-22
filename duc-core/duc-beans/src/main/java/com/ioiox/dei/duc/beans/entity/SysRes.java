package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.BaseDeiEnum;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.constant.DeiStatus;
import com.ioiox.dei.duc.spring.core.model.DUCSysResType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SysRes extends BaseDeiEntity {
    private String code;
    private String name;
    private String type;
    private String status;
    private String memo;
    private String sysPrjModuleName;
    private String sysPrjModuleCode;
    private Long sysPrjSid;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(type)) {
            type = DUCSysResType.SHARED.getCode();
        }
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
        if (StringUtils.isBlank(sysPrjModuleName)) {
            sysPrjModuleName = DeiGlobalConstant.NONE_CN;
        }
        if (StringUtils.isBlank(sysPrjModuleCode)) {
            sysPrjModuleCode = DeiGlobalConstant.NONE;
        }
        if (Objects.isNull(sysPrjSid)) {
            sysPrjSid = DeiGlobalConstant.DEFAULT_SID;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRes that = (SysRes) o;
        if (Objects.nonNull(getSid())
                && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return Objects.equals(code, that.code)
                    && Objects.equals(sysPrjSid, that.sysPrjSid);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, sysPrjSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "资源编号"),
        NAME("name", "资源名称"),
        TYPE("type", "资源类型"),
        STATUS("status", "资源状态"),
        MEMO("memo", "备注"),
        SYS_PRJ_MODULE_NAME("sysPrjModuleName", "资源所属所属模块名称"),
        SYS_PRJ_MODULE_CODE("sysPrjModuleCode", "资源所属所属模块编号"),
        SYS_PRJ_SID("sysPrjSid", "资源所属项目主键ID"),
        ;
        private final String code;
        private final String desc;
    }
}
