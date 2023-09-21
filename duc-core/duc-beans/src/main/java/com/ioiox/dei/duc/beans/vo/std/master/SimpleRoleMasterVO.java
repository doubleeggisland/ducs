package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import com.ioiox.dei.duc.spring.core.model.DUCRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class SimpleRoleMasterVO extends MasterStdDataVO {
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
     * 所属项目ID
     */
    private Long sysPrjId;

    /**
     * 不限制日期范围 (Y: 是, N: 否)
     */
    private String unlimitedDateRange;

    private Long effectiveStartDate;

    private Long effectiveEndDate;
    /**
     * 不限制时间范围 (Y: 是, N: 否)
     */
    private String unlimitedTimeRange;

    private Long effectiveStartTime;

    private Long effectiveEndTime;
}
