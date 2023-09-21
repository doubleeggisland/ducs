package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SimpleRoleMasterVO;

public interface RoleMasterStdDataSvc<T extends SimpleRoleMasterVO, D extends SimpleRoleDelParam> {
    Long save(final T role);
    boolean update(final T role);
    int remove(final D delParam);
}
