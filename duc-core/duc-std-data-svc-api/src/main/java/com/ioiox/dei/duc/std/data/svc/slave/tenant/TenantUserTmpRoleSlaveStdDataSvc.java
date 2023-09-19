package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface TenantUserTmpRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<TenantUserTmpRoleSlaveVO, TenantUserTmpRoleQueryParam> {

    List<TenantUserTmpRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                    final RoleQueryCfg queryCfg);

    TenantUserTmpRoleSlaveVO queryByPk(final Long pk,
                                       final RoleQueryCfg queryCfg);

    List<TenantUserTmpRoleSlaveVO> queryByPks(final List<Long> pks,
                                              final RoleQueryCfg queryCfg);
}
