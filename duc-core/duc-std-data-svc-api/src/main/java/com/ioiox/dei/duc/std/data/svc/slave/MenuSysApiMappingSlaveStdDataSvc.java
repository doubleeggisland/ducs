package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;

import java.util.List;

public interface MenuSysApiMappingSlaveStdDataSvc {
    List<MenuSysApiMappingSlaveStdVO> queryByPks(final List<Long> sysApiMappingIds,
                                                 final MenuSysApiMappingQueryCfg queryCfg);
    List<MenuSysApiMappingSlaveStdVO> queryByMenuIds(final List<Long> menuIds,
                                                     final MenuSysApiMappingQueryCfg queryCfg);
    List<MenuSysApiMappingSlaveStdVO> queryByParam(final MenuSysApiMappingQueryParam queryParam,
                                                   final MenuSysApiMappingQueryCfg queryCfg);
}
