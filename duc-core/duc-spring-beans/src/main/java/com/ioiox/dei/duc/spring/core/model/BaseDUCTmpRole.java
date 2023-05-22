package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDUCTmpRole extends BaseDUCRole {
    /**
     * 不限制日期范围 (Y: 是, N: 否)
     */
    private String unlimitedDateRangeFlag;
    /**
     * 生效开始日期
     */
    private Long effectiveStartDate;
    /**
     * 生效结束日期
     */
    private Long effectiveEndDate;
    /**
     * 不限制时间范围 (Y: 是, N: 否)
     */
    private String unlimitedTimeRangeFlag;
    /**
     * 生效开始时间
     */
    private Long effectiveStartTime;
    /**
     * 生效结束日期
     */
    private Long effectiveEndTime;

    public BaseDUCTmpRole(final BaseDUCTmpRoleBuilder<? extends BaseDUCTmpRole> builder) {
        super(builder);
        unlimitedDateRangeFlag = builder.unlimitedDateRangeFlag();
        effectiveStartDate = builder.effectiveStartDate();
        effectiveEndDate = builder.effectiveEndDate();
        unlimitedTimeRangeFlag = builder.unlimitedTimeRangeFlag();
        effectiveStartTime = builder.effectiveStartTime();
        effectiveEndTime = builder.effectiveEndTime();
    }
}
