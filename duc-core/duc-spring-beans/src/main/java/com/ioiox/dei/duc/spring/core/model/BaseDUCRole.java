package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDUCRole {
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
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;

    public BaseDUCRole(final BaseDUCRoleBuilder<? extends BaseDUCRole> builder) {
        id = builder.id();
        code = builder.code();
        name = builder.name();
        type = builder.type();
        sysPrjId = builder.sysPrjId();
        status = builder.status();
    }
}
