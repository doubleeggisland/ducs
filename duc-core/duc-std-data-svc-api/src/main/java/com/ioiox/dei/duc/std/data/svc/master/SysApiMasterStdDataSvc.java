package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SysApiDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterStdVO;

public interface SysApiMasterStdDataSvc {
    Long save(final SysApiMasterStdVO sysApi);
    boolean update(final SysApiMasterStdVO sysApi);
    int remove(final SysApiDelParam delParam);
}
