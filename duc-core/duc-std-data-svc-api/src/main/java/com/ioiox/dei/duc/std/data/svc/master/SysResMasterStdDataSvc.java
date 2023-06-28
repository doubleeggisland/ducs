package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SysResDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterStdVO;

public interface SysResMasterStdDataSvc {
    Long save(final SysResMasterStdVO sysRes);
    boolean update(final SysResMasterStdVO sysRes);
    int remove(final SysResDelParam delParam);
}
