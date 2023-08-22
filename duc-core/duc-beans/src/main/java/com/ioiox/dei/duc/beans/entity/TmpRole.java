package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.constant.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

/**
 * 临时角色, 用于给用户临时分配菜单和功能权限
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class TmpRole
        extends Role {
    private String unlimitedDateRangeFlag;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private String unlimitedTimeRangeFlag;
    private Time effectiveStartTime;
    private Time effectiveEndTime;

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        UNLIMITED_DATE_RANGE("unlimitedDateRangeFlag", "不限制日期 (Y: 是, N: 否)"),
        EFFECTIVE_START_DATE("effectiveStartDate", "生效开始日期"),
        EFFECTIVE_END_DATE("effectiveEndDate", "生效结束日期"),
        UNLIMITED_TIME_RANGE("unlimitedTimeRangeFlag", "不限制时间 (Y: 是, N: 否)"),
        EFFECTIVE_START_TIME("effectiveStartTime", "生效开始时间"),
        EFFECTIVE_END_TIME("effectiveEndTime", "生效结束时间"),
        ;
        private final String code;
        private final String desc;
    }
}
