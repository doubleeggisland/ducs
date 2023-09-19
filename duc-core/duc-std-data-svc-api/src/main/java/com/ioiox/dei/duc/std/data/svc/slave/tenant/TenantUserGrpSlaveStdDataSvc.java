package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserGrpSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.UserGrpSlaveStdDataSvc;

import java.util.List;

public interface TenantUserGrpSlaveStdDataSvc
        extends UserGrpSlaveStdDataSvc<TenantUserRoleSlaveVO, TenantUserSysResRoleSlaveVO, TenantUserGrpSlaveVO, TenantUserGrpQueryParam> {

    List<TenantUserGrpSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                final UserGrpQueryCfg queryCfg);

    TenantUserGrpSlaveVO queryByPk(final Long pk,
                                   final UserGrpQueryCfg queryCfg);

    List<TenantUserGrpSlaveVO> queryByPks(final List<Long> pks,
                                          final UserGrpQueryCfg queryCfg);
}
