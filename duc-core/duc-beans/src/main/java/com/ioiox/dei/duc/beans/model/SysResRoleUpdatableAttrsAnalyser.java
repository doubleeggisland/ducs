package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveStdVO;

public abstract class SysResRoleUpdatableAttrsAnalyser<
        M extends BaseRoleMasterStdVO,
        S extends BaseRoleSlaveStdVO,
        O extends SysResRoleUpdatableObj,
        C extends SysResRoleUpdateCtx<O>>
        extends RoleUpdatableAttrsAnalyser<M, S, O, C> {

}
