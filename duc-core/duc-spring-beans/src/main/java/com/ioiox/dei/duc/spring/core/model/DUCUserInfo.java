package com.ioiox.dei.duc.spring.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DUCUserInfo {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登陆密码
     */
    @JsonIgnore
    private String pwd;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态
     */
    private String status;
    /**
     * 个人头像URL
     */
    private String avatarUrl;
    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 所属企业ID
     */
    private Long corpId;
    /**
     * 用户类型
     * @see DUCUserType
     */
    private String userType;

    private DUCUserInfo(final Builder builder) {
        userId = builder.userId;
        username = builder.username;
        nickName = builder.nickName;
        realName = builder.realName;
        mobile = builder.mobile;
        email = builder.email;
        status = builder.status;
        avatarUrl = builder.avatarUrl;
        tenantId = builder.tenantId;
        corpId = builder.corpId;
        userType = builder.userType;
    }

    public static class Builder {
        private Long userId;
        private String username;
        private String pwd;
        private String nickName;
        private String realName;
        private String mobile;
        private String email;
        private String status;
        private String avatarUrl;
        private Long tenantId;
        private Long corpId;
        private String userType;

        public Builder userId(final Long userId) {
            this.userId = userId;
            return this;
        }
        public Builder username(final String username) {
            this.username = username;
            return this;
        }
        public Builder pwd(final String pwd) {
            this.pwd = pwd;
            return this;
        }
        public Builder nickName(final String nickName) {
            this.nickName = nickName;
            return this;
        }
        public Builder realName(final String realName) {
            this.realName = realName;
            return this;
        }
        public Builder mobile(final String mobile) {
            this.mobile = mobile;
            return this;
        }
        public Builder email(final String email) {
            this.email = email;
            return this;
        }
        public Builder status(final String status) {
            this.status = status;
            return this;
        }
        public Builder avatarUrl(final String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }
        public Builder tenantId(final Long tenantId) {
            this.tenantId = tenantId;
            return this;
        }
        public Builder corpId(final Long corpId) {
            this.corpId = corpId;
            return this;
        }
        public Builder userType(final String userType) {
            this.userType = userType;
            return this;
        }

        public DUCUserInfo build() {
            return new DUCUserInfo(this);
        }
    }
}
