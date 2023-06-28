package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SysPrjDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterStdVO;

public interface SysPrjMasterStdDataSvc {
    Long save(final SysPrjMasterStdVO sysPrj);
    boolean update(final SysPrjMasterStdVO sysPrj);
    int remove(final SysPrjDelParam delParam);
}
