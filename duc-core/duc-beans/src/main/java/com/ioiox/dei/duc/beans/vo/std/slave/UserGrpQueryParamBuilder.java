package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;

import java.util.List;

public abstract class UserGrpQueryParamBuilder<T extends UserGrpQueryParam>
        extends StdDataQueryParamBuilder<T> {
    private List<String> codes;
    private List<String> statuses;

    public UserGrpQueryParamBuilder<T> codes(final List<String> codes) {
        this.codes = codes;
        return this;
    }
    public UserGrpQueryParamBuilder<T> statuses(final List<String> statuses) {
        this.statuses = statuses;
        return this;
    }

    public List<String> codes() {
        return codes;
    }
    public List<String> statuses() {
        return statuses;
    }
}
