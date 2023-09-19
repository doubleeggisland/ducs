package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface TenantUserTmpSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<TenantUserTmpSysResRoleSlaveVO, TenantUserTmpSysResRoleQueryParam> {

    List<TenantUserTmpSysResRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                          final SysResRoleQueryCfg queryCfg);

    TenantUserTmpSysResRoleSlaveVO queryByPk(final Long pk,
                                             final SysResRoleQueryCfg queryCfg);

    List<TenantUserTmpSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                    final SysResRoleQueryCfg queryCfg);
}
