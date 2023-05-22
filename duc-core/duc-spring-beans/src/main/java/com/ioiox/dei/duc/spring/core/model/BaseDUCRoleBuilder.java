package com.ioiox.dei.duc.spring.core.model;

public abstract class BaseDUCRoleBuilder<T extends BaseDUCRole> {
    private Long id;
    private String code;
    private String name;
    private String type;
    private Long sysPrjId;
    private String status;

    public BaseDUCRoleBuilder<T> id(final Long id) {
        this.id = id;
        return this;
    }
    public BaseDUCRoleBuilder<T> code(final String code) {
        this.code = code;
        return this;
    }
    public BaseDUCRoleBuilder<T> name(final String name) {
        this.name = name;
        return this;
    }
    public BaseDUCRoleBuilder<T> type(final String type) {
        this.type = type;
        return this;
    }
    public BaseDUCRoleBuilder<T> sysPrjId(final Long sysPrjId) {
        this.sysPrjId = sysPrjId;
        return this;
    }
    public BaseDUCRoleBuilder<T> status(final String status) {
        this.status = status;
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

    public abstract T build();
}
