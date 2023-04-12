package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.model.RoleUpdatableObj;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;

public abstract class BaseSysResRoleMasterStdDataSvc<
        T extends BaseRoleMasterStdVO,
        O extends RoleUpdatableObj,
        E extends Role>
        extends CommonRoleMasterStdDataSvc<T, O, E> {
}
