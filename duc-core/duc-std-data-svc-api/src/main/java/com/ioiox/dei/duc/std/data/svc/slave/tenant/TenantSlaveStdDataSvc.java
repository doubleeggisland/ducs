package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveVO;

import java.util.List;

public interface TenantSlaveStdDataSvc {
    int countByParam(final TenantQueryParam queryParam);
    List<TenantSlaveVO> queryByParam(final TenantQueryParam queryParam,
                                     final TenantQueryCfg queryCfg);
}
