package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveStdVO;

import java.util.List;

public interface TenantSlaveStdDataSvc {
    int countByParam(final TenantQueryParam queryParam);
    List<TenantSlaveStdVO> queryByParam(final TenantQueryParam queryParam,
                                        final TenantQueryCfg queryCfg);
}
