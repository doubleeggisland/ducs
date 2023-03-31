package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveStdVO;

import java.util.List;

public interface MenuSlaveStdDataSvc {
    int countByParam (final MenuQueryParam queryParam);
    List<MenuSlaveStdVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                          final MenuQueryCfg queryCfg);
    List<MenuSlaveStdVO> queryByPks(final List<Long> menuIds,
                                    final MenuQueryCfg queryCfg);
    List<MenuSlaveStdVO> queryByParam(final MenuQueryParam queryParam,
                                      final MenuQueryCfg queryCfg);
}
