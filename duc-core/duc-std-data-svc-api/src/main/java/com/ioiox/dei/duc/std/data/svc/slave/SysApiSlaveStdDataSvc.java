package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.core.vo.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveStdVO;

import java.util.List;

public interface SysApiSlaveStdDataSvc {
    int countByParam(final SysApiQueryParam queryParam);
    List<SysApiSlaveStdVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                            final StdDataQueryCfg queryCfg);
    SysApiSlaveStdVO queryByPk(final Long sysApiId, final StdDataQueryCfg queryCfg);
    List<SysApiSlaveStdVO> queryByPks(final List<Long> sysApiIds, final StdDataQueryCfg queryCfg);
    List<SysApiSlaveStdVO> queryByParam(final SysApiQueryParam queryParam,
                                        final StdDataQueryCfg queryCfg);
}
