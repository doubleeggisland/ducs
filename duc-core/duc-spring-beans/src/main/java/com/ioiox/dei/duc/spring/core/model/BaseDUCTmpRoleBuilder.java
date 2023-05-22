package com.ioiox.dei.duc.spring.core.model;

public abstract class BaseDUCTmpRoleBuilder<T extends BaseDUCTmpRole>
        extends BaseDUCRoleBuilder<T> {
    private String unlimitedDateRangeFlag;
    private Long effectiveStartDate;
    private Long effectiveEndDate;
    private String unlimitedTimeRangeFlag;
    private Long effectiveStartTime;
    private Long effectiveEndTime;

    public BaseDUCTmpRoleBuilder<T> unlimitedDateRangeFlag(final String unlimitedDateRangeFlag) {
        this.unlimitedDateRangeFlag = unlimitedDateRangeFlag;
        return this;
    }
    public BaseDUCTmpRoleBuilder<T> effectiveStartDate(final Long effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
        return this;
    }
    public BaseDUCTmpRoleBuilder<T> effectiveEndDate(final Long effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
        return this;
    }
    public BaseDUCTmpRoleBuilder<T> unlimitedTimeRangeFlag(final String unlimitedTimeRangeFlag) {
        this.unlimitedTimeRangeFlag = unlimitedTimeRangeFlag;
        return this;
    }
    public BaseDUCTmpRoleBuilder<T> effectiveStartTime(final Long effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
        return this;
    }
    public BaseDUCTmpRoleBuilder<T> effectiveEndTime(final Long effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
        return this;
    }

    public String unlimitedDateRangeFlag() {
        return unlimitedDateRangeFlag;
    }

    public Long effectiveStartDate() {
        return effectiveStartDate;
    }

    public Long effectiveEndDate() {
        return effectiveEndDate;
    }

    public String unlimitedTimeRangeFlag() {
        return unlimitedTimeRangeFlag;
    }

    public Long effectiveStartTime() {
        return effectiveStartTime;
    }

    public Long effectiveEndTime() {
        return effectiveEndTime;
    }
}
