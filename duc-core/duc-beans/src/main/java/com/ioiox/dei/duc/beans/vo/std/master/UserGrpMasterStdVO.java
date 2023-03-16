package com.ioiox.dei.duc.beans.vo.std.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserGrpMasterStdVO extends BaseUserGrpMasterStdVO {
    /**
     * 给用户组分配的角色
     */
    private List<Long> roleIds;
    /**
     * 给用户组分配的资源角色
     */
    private List<Long> sysResRoleIds;
}
