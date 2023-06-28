package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.model.slave.SysPrjQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysPrjQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysPrjSlaveStdVO;

import java.util.List;

public interface SysPrjSlaveStdDataSvc {
    int countByParam(final SysPrjQueryParam queryParam);
    SysPrjSlaveStdVO queryByPk(final Long sysPrjId,
                               final SysPrjQueryCfg queryCfg);
    List<SysPrjSlaveStdVO> queryByPks(final List<Long> sysPrjIds,
                                      final SysPrjQueryCfg queryCfg);
    List<SysPrjSlaveStdVO> queryByParam(final SysPrjQueryParam queryParam,
                                        final SysPrjQueryCfg queryCfg);
}
