package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;

import java.util.List;

public abstract class UserQueryParamBuilder<T extends UserQueryParam>
        extends StdDataQueryParamBuilder<T> {
    private List<String> usernames;
    private List<String> mobiles;
    private List<String> emails;
    private List<String> statuses;
    private String nickNameLike;

    public UserQueryParamBuilder<T> usernames(final List<String> usernames) {
        this.usernames = usernames;
        return this;
    }
    public UserQueryParamBuilder<T> mobiles(final List<String> mobiles) {
        this.mobiles = mobiles;
        return this;
    }
    public UserQueryParamBuilder<T> emails(final List<String> emails) {
        this.emails = emails;
        return this;
    }
    public UserQueryParamBuilder<T> statuses(final List<String> statuses) {
        this.statuses = statuses;
        return this;
    }
    public UserQueryParamBuilder<T> nickNameLike(final String nickNameLike) {
        this.nickNameLike = nickNameLike;
        return this;
    }

    public List<String> usernames() {
        return usernames;
    }

    public List<String> mobiles() {
        return mobiles;
    }

    public List<String> emails() {
        return emails;
    }

    public List<String> statuses() {
        return statuses;
    }

    public String nickNameLike() {
        return nickNameLike;
    }
}
