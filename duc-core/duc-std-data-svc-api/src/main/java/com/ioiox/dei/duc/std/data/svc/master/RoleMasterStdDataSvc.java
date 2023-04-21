package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;

public interface RoleMasterStdDataSvc<T extends BaseRoleMasterStdVO, D extends BaseRoleDelParam> {
    Long save(final T role);
    boolean update(final T role);
    int remove(final D delParam);
}
