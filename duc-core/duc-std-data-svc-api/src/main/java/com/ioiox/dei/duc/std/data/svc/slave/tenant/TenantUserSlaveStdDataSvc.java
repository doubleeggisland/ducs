package com.ioiox.dei.duc.std.data.svc.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.*;
import com.ioiox.dei.duc.std.data.svc.slave.UserSlaveStdDataSvc;

import java.util.List;

public interface TenantUserSlaveStdDataSvc
        extends UserSlaveStdDataSvc<TenantUserRoleSlaveVO, TenantUserSysResRoleSlaveVO, TenantUserTmpRoleSlaveVO, TenantUserTmpSysResRoleSlaveVO, TenantUserGrpSlaveVO, TenantUserSlaveVO, TenantUserQueryParam> {

    List<TenantUserSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                             final UserQueryCfg queryCfg);
}
