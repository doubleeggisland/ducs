package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;

import com.ioiox.dei.core.constant.BaseDeiEnum;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.constant.DeiStatus;
import com.ioiox.dei.core.utils.DateUtil;
import com.ioiox.dei.duc.spring.core.model.DUCRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class SimpleRole extends BaseDeiEntity {
    /**
     * 角色编号
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型
     * @see DUCRoleType
     */
    private String type;
    /**
     * 状态
     * @see com.ioiox.dei.core.constant.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 所属系统ID
     */
    private Long sysPrjSid;

    /**
     * 不限制日期 (Y: 是, N: 否)
     */
    private String unlimitedDateRange;

    private Date effectiveStartDate;

    private Date effectiveEndDate;
    /**
     * 不限制时间 (Y: 是, N: 否)
     */
    private String unlimitedTimeRange;

    private Time effectiveStartTime;

    private Time effectiveEndTime;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(type)) {
            type = DUCRoleType.CUSTOMIZED.getCode();
        }
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
        if (Objects.isNull(sysPrjSid)) {
            sysPrjSid = DeiGlobalConstant.DEFAULT_SID;
        }
        if (StringUtils.isBlank(unlimitedDateRange)) {
            unlimitedDateRange = DeiGlobalConstant.FLAG_YES;
        }
        if (Objects.isNull(effectiveStartDate)) {
            effectiveStartDate = new Date(System.currentTimeMillis());
        }
        if (Objects.isNull(effectiveEndDate)) {
            effectiveEndDate = DateUtil.addYears(effectiveStartDate, 50);
        }
        if (StringUtils.isBlank(unlimitedTimeRange)) {
            unlimitedTimeRange = DeiGlobalConstant.FLAG_YES;
        }
        if (Objects.isNull(effectiveStartTime)) {
            effectiveStartTime = Time.valueOf("00:00:00");
        }
        if (Objects.isNull(effectiveEndTime)) {
            effectiveEndTime = Time.valueOf("23:59:59");
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "角色编号"),
        NAME("name", "角色名称"),
        TYPE("type", "角色类型"),
        STATUS("status", "状态"),
        MEMO("memo", "备注"),
        SYS_PRJ_SID("sysPrjSid", "所属系统ID"),
        UNLIMITED_DATE_RANGE("unlimitedDateRange", "不限制日期 (Y: 是, N: 否)"),
        EFFECTIVE_START_DATE("effectiveStartDate", "生效开始日期"),
        EFFECTIVE_END_DATE("effectiveEndDate", "生效结束日期"),
        UNLIMITED_TIME_RANGE("unlimitedTimeRange", "不限制时间 (Y: 是, N: 否)"),
        EFFECTIVE_START_TIME("effectiveStartTime", "生效开始时间"),
        EFFECTIVE_END_TIME("effectiveEndTime", "生效结束时间"),
        ;
        private final String code;
        private final String desc;
    }
}
