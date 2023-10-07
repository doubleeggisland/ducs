package com.ioiox.dei.duc.spring.core.model;

public abstract class DUCSimpleRoleBuilder<T extends DUCSimpleRole> {
    private Long id;
    private String code;
    private String name;
    private String type;
    private Long sysPrjId;
    private String status;
    private DUCDatetimeRangeBasedAuthRule authRule;

    public DUCSimpleRoleBuilder<T> id(final Long id) {
        this.id = id;
        return this;
    }
    public DUCSimpleRoleBuilder<T> code(final String code) {
        this.code = code;
        return this;
    }
    public DUCSimpleRoleBuilder<T> name(final String name) {
        this.name = name;
        return this;
    }
    public DUCSimpleRoleBuilder<T> type(final String type) {
        this.type = type;
        return this;
    }
    public DUCSimpleRoleBuilder<T> sysPrjId(final Long sysPrjId) {
        this.sysPrjId = sysPrjId;
        return this;
    }
    public DUCSimpleRoleBuilder<T> status(final String status) {
        this.status = status;
        return this;
    }
    public DUCSimpleRoleBuilder<T> authRule(final DUCDatetimeRangeBasedAuthRule authRule) {
        this.authRule = authRule;
        return this;
    }

    public Long id() {
        return id;
    }
    public String code() {
        return code;
    }
    public String name() {
        return name;
    }
    public String type() {
        return type;
    }
    public Long sysPrjId() {
        return sysPrjId;
    }
    public String status() {
        return status;
    }
    public DUCDatetimeRangeBasedAuthRule authRule() {
        return authRule;
    }

    public abstract T build();
}
