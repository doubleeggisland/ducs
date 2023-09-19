package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface TenantUserRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<TenantUserRoleSlaveVO, TenantUserRoleQueryParam> {

    List<TenantUserRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                 final RoleQueryCfg queryCfg);

    TenantUserRoleSlaveVO queryByPk(final Long pk, final RoleQueryCfg queryCfg);

    List<TenantUserRoleSlaveVO> queryByPKs(final List<Long> pks, final RoleQueryCfg queryCfg);
}
