package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.MenuDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterVO;

import java.util.List;

public interface MenuMasterStdDataSvc {
    Long save(final MenuMasterVO menu);
    boolean update(final MenuMasterVO menu);
    int remove(final MenuDelParam delParam);
    void remove(final List<Long> menuIds);
}
