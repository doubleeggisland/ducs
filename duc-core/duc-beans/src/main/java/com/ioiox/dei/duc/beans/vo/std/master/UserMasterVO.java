package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserMasterVO extends MasterStdDataVO {
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
    /**
     * 分配的用户组
     */
    private List<Long> userGrpIds;
    /**
     * 分配的项目权限
     */
    private List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges;
    /**
     * 分配的角色
     */
    private List<Long> roleIds;
    /**
     * 分配的系统资源角色
     */
    private List<Long> sysResRoleIds;
    /**
     * 分配的临时角色
     */
    private List<Long> tmpRoleIds;
    /**
     * 分配的临时系统资源角色
     */
    private List<Long> tmpSysResRoleIds;


}
