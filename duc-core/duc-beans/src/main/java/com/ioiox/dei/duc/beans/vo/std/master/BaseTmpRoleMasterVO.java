package com.ioiox.dei.duc.beans.vo.std.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseTmpRoleMasterVO extends BaseRoleMasterVO {
    /**
     * 不限制日期范围 (Y: 是, N: 否)
     */
    private String unlimitedDateRangeFlag;
    private Long effectiveStartDate;
    private Long effectiveEndDate;
    /**
     * 不限制时间范围 (Y: 是, N: 否)
     */
    private String unlimitedTimeRangeFlag;
    private Long effectiveStartTime;
    private Long effectiveEndTime;
}
