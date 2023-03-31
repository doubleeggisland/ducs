package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.core.vo.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveStdVO;

import java.util.List;

public interface SysResSlaveStdDataSvc {
    int countByParam(final SysResQueryParam queryParam);
    List<SysResSlaveStdVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                            final StdDataQueryCfg queryCfg);
    List<SysResSlaveStdVO> queryByPks(final List<Long> sysResIds,
                                      final StdDataQueryCfg queryCfg);
    List<SysResSlaveStdVO> queryByParam(final SysResQueryParam queryParam,
                                        final StdDataQueryCfg queryCfg);
}
