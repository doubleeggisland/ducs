package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SysResDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterVO;

public interface SysResMasterStdDataSvc {
    Long save(final SysResMasterVO sysRes);
    boolean update(final SysResMasterVO sysRes);
    int remove(final SysResDelParam delParam);
}
