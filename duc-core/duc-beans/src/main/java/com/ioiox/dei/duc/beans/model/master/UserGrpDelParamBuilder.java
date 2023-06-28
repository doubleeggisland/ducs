package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.vo.StdDataDelParamBuilder;

import java.util.List;

public abstract class UserGrpDelParamBuilder<T extends UserGrpDelParam>
        extends StdDataDelParamBuilder<T> {
    private List<String> statuses;

    public UserGrpDelParamBuilder<T> statuses(final List<String> statuses) {
        this.statuses = statuses;
        return this;
    }

    public List<String> statuses() {
        return statuses;
    }
}
