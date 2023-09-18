package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysApiQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;

import java.util.List;

public interface SysApiSlaveStdDataSvc {
    int countByParam(final SysApiQueryParam queryParam);
    List<SysApiSlaveVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                         final StdDataQueryCfg queryCfg);
    SysApiSlaveVO queryByPk(final Long sysApiId, final StdDataQueryCfg queryCfg);
    List<SysApiSlaveVO> queryByPks(final List<Long> sysApiIds, final StdDataQueryCfg queryCfg);
    List<SysApiSlaveVO> queryByParam(final SysApiQueryParam queryParam,
                                     final StdDataQueryCfg queryCfg);
}
