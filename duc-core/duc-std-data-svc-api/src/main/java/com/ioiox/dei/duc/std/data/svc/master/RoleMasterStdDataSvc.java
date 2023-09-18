package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterVO;

public interface RoleMasterStdDataSvc<T extends BaseRoleMasterVO, D extends BaseRoleDelParam> {
    Long save(final T role);
    boolean update(final T role);
    int remove(final D delParam);
}
