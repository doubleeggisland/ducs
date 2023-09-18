package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SysApiDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterVO;

public interface SysApiMasterStdDataSvc {
    Long save(final SysApiMasterVO sysApi);
    boolean update(final SysApiMasterVO sysApi);
    int remove(final SysApiDelParam delParam);
}
