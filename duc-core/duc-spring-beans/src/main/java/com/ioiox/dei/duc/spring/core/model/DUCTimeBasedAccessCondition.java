package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DUCTimeBasedAccessCondition {
    private String unlimitedDateRange;
    private Long effectiveStartDate;
    private Long effectiveEndDate;
    private String unlimitedTimeRange;
    private Long effectiveStartTime;
    private Long effectiveEndTime;

    private DUCTimeBasedAccessCondition(final Builder builder) {
        unlimitedDateRange = builder.unlimitedDateRange;
        effectiveStartDate = builder.effectiveStartDate;
        effectiveEndDate = builder.effectiveEndDate;
        unlimitedTimeRange = builder.unlimitedTimeRange;
        effectiveStartTime = builder.effectiveStartTime;
        effectiveEndTime = builder.effectiveEndTime;
    }

    public static class Builder {
        private String unlimitedDateRange;
        private Long effectiveStartDate;
        private Long effectiveEndDate;
        private String unlimitedTimeRange;
        private Long effectiveStartTime;
        private Long effectiveEndTime;

        public Builder unlimitedDateRange(final String unlimitedDateRange) {
            this.unlimitedDateRange = unlimitedDateRange;
            return this;
        }
        public Builder effectiveStartDate(final Long effectiveStartDate) {
            this.effectiveStartDate = effectiveStartDate;
            return this;
        }
        public Builder effectiveEndDate(final Long effectiveEndDate) {
            this.effectiveEndDate = effectiveEndDate;
            return this;
        }
        public Builder unlimitedTimeRange(final String unlimitedTimeRange) {
            this.unlimitedTimeRange = unlimitedTimeRange;
            return this;
        }
        public Builder effectiveStartTime(final Long effectiveStartTime) {
            this.effectiveStartTime = effectiveStartTime;
            return this;
        }
        public Builder effectiveEndTime(final Long effectiveEndTime) {
            this.effectiveEndTime = effectiveEndTime;
            return this;
        }

        public DUCTimeBasedAccessCondition build() {
            return new DUCTimeBasedAccessCondition(this);
        }
    }
}
