package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface TenantUserSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<TenantUserSysResRoleSlaveVO, TenantUserSysResRoleQueryParam> {

    List<TenantUserSysResRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                       final SysResRoleQueryCfg queryCfg);

    TenantUserSysResRoleSlaveVO queryByPk(final Long pk,
                                          final SysResRoleQueryCfg queryCfg);

    List<TenantUserSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                 final SysResRoleQueryCfg queryCfg);
}
