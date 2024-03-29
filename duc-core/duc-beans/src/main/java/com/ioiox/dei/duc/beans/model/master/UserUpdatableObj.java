package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserUpdatableObj extends UpdatableObj {

    /**
     * 用户名
     */
    private UpdatableAttr<String> username;
    /**
     * 昵称
     */
    private UpdatableAttr<String> nickName;
    /**
     * 手机号
     */
    private UpdatableAttr<String> mobile;
    /**
     * 邮箱
     */
    private UpdatableAttr<String> email;
    /**
     * 状态
     */
    private UpdatableAttr<String> status;
    /**
     * 登录密码
     */
    private UpdatableAttr<String> pwd;
    /**
     * 个人头像URL
     */
    private UpdatableAttr<String> avatarUrl;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(BaseUser.ShowColumn.values().length);
        if (Objects.nonNull(username)) {
            updateSummary.put(username.getAttrName(), String.valueOf(username));
        }
        if (Objects.nonNull(nickName)) {
            updateSummary.put(nickName.getAttrName(), String.valueOf(nickName));
        }
        if (Objects.nonNull(mobile)) {
            updateSummary.put(mobile.getAttrName(), String.valueOf(mobile));
        }
        if (Objects.nonNull(email)) {
            updateSummary.put(email.getAttrName(), String.valueOf(email));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        if (Objects.nonNull(pwd)) {
            updateSummary.put(pwd.getAttrName(), String.valueOf(pwd));
        }
        if (Objects.nonNull(avatarUrl)) {
            updateSummary.put(avatarUrl.getAttrName(), String.valueOf(avatarUrl));
        }
        return updateSummary;
    }
}
