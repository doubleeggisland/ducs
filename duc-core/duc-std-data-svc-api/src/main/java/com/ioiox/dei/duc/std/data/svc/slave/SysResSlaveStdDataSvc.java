package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;

import java.util.List;

public interface SysResSlaveStdDataSvc {
    int countByParam(final SysResQueryParam queryParam);
    List<SysResSlaveVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                         final StdDataQueryCfg queryCfg);
    SysResSlaveVO queryByPk(final Long sysResId,
                            final StdDataQueryCfg queryCfg);
    List<SysResSlaveVO> queryByPks(final List<Long> sysResIds,
                                   final StdDataQueryCfg queryCfg);
    List<SysResSlaveVO> queryByParam(final SysResQueryParam queryParam,
                                     final StdDataQueryCfg queryCfg);
}
