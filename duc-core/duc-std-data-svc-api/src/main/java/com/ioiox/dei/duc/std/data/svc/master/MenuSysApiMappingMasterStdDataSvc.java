package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.MenuSysApiMappingDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuSysApiMappingMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;

import java.util.List;

public interface MenuSysApiMappingMasterStdDataSvc {
    int sync(final List<MenuSysApiMappingMasterVO> sysApiMappings,
             final List<MenuSysApiMappingSlaveStdVO> existingSysApiMappings);
    Long save(final MenuSysApiMappingMasterVO sysApiMapping);
    int remove(final MenuSysApiMappingDelParam delParam);
}
