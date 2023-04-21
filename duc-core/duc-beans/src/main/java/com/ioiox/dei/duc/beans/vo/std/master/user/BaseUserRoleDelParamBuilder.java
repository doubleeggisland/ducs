package com.ioiox.dei.duc.beans.vo.std.master.user;

import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleDelParamBuilder;

import java.util.List;

public abstract class BaseUserRoleDelParamBuilder<T extends BaseUserRoleDelParam>
        extends BaseRoleDelParamBuilder<T> {
    private List<Long> corpIds;

    public BaseUserRoleDelParamBuilder<T> corpIds(final List<Long> corpIds) {
        this.corpIds = corpIds;
        return this;
    }

    public List<Long> corpIds() {
        return  corpIds;
    }
}
