package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveVO;

import java.util.List;

public interface TenantSlaveStdDataSvc {

    int countByParam(final TenantQueryParam queryParam);

    TenantSlaveVO queryByPk(final Long pk,
                            final TenantQueryCfg queryCfg);

    List<TenantSlaveVO> queryByPks(final List<Long> pks,
                                   final TenantQueryCfg queryCfg);

    List<TenantSlaveVO> queryByParam(final TenantQueryParam queryParam,
                                     final TenantQueryCfg queryCfg);
}
