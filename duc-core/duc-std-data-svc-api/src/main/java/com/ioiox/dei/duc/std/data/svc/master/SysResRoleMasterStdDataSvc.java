package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SimpleRoleMasterVO;

public interface SysResRoleMasterStdDataSvc<T extends SimpleRoleMasterVO, D extends SimpleRoleDelParam> {
    Long save(final T sysResRole);
    boolean update(final T sysResRole);
    int remove(final D delParam);
}
