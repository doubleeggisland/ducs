package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.SlaveStdDataVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSlaveStdVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class AcctSlaveStdVO<
        R extends RoleSlaveStdVO,
        RR extends SysResRoleSlaveStdVO,
        TR extends TmpRoleSlaveStdVO,
        TRR extends TmpSysResRoleSlaveStdVO,
        UG extends UserGrpSlaveStdVO<R, RR>>
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

    /**
     * 分配的用户组
     */
    private UG userGrp;
    /**
     * 分配的项目权限
     */
    private List<UserSysPrjPrivilegeSlaveStdVO> sysPrjPrivileges;
    /**
     * 分配的角色
     */
    private List<R> role;
    /**
     * 分配的系统资源角色
     */
    private List<RR> sysResRoles;
    /**
     * 分配的临时角色
     */
    private List<TR> tmpRoles;
    /**
     * 分配的临时系统资源角色
     */
    private List<TRR> tmpSysResRoles;
}
