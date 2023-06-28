package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;

public interface SysResRoleMasterStdDataSvc<T extends BaseRoleMasterStdVO, D extends BaseRoleDelParam> {
    Long save(final T sysResRole);
    boolean update(final T sysResRole);
    int remove(final D delParam);
}
