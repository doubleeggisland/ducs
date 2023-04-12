package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.vo.std.master.MenuSysApiMappingDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuSysApiMappingMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;

import java.util.List;

public interface MenuSysApiMappingMasterStdDataSvc {
    int sync(final List<MenuSysApiMappingMasterStdVO> sysApiMappings,
             final List<MenuSysApiMappingSlaveStdVO> existingSysApiMappings);
    Long save(final MenuSysApiMappingMasterStdVO sysApiMapping);
    int remove(final MenuSysApiMappingDelParam delParam);
}
