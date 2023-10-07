package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class DUCSimpleRole {
    /**
     * 角色ID
     */
    private Long id;
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
     * 角色所属项目ID
     */
    private Long sysPrjId;
    /**
     * 角色状态
     * @see com.ioiox.dei.core.constant.DeiStatus
     */
    private String status;
    /**
     * 基于日期范围和时间范围的授权规则
     */
    private DUCDatetimeRangeBasedAuthRule authRule;

    public DUCSimpleRole(final DUCSimpleRoleBuilder<? extends DUCSimpleRole> builder) {
        id = builder.id();
        code = builder.code();
        name = builder.name();
        type = builder.type();
        sysPrjId = builder.sysPrjId();
        status = builder.status();
        authRule = builder.authRule();
    }
}
