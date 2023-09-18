package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.SysPrjDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterVO;

public interface SysPrjMasterStdDataSvc {
    Long save(final SysPrjMasterVO sysPrj);
    boolean update(final SysPrjMasterVO sysPrj);
    int remove(final SysPrjDelParam delParam);
}
