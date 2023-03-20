package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.beans.DeiStatus;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseUser extends BaseDeiEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
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
     * 登录密码
     */
    private String pwd;
    /**
     * 个人头像URL
     */
    private String avatarUrl;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(nickName)) {
            nickName = DeiGlobalConstant.UNKNOWN_CN;
        }
        if (StringUtils.isBlank(mobile)) {
            mobile = DeiGlobalConstant.NONE;
        }
        if (StringUtils.isBlank(email)) {
            email = DeiGlobalConstant.NONE;
        }
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        USER_NAME("username", "用户名"),
        NICK_NAME("nickName", "昵称"),
        MOBILE("mobile", "手机号"),
        EMAIL("email", "邮箱"),
        STATUS("status", "状态"),
        PWD("pwd", "登录密码"),
        AVATAR_URL("avatarUrl", "个人头像URL"),
        ;
        private final String code;
        private final String desc;
    }
}
