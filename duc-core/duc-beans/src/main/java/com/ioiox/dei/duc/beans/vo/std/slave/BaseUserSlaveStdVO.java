package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseUserSlaveStdVO
        extends SlaveStdDataVO {
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
}
