package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.vo.std.master.MenuDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveStdVO;

import java.util.List;

public interface MenuMasterStdDataSvc {
    Long save(final MenuMasterStdVO menu);
    boolean update(final MenuMasterStdVO menu);
    void remove(final MenuDelParam delParam);
    void remove(final List<Long> menuIds);
}
