package com.ioiox.dei.duc.std.data.svc.master.tenant;

import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserSysResRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.SysResRoleMasterStdDataSvc;

public interface TenantUserSysResRoleMasterStdDataSvc
        extends SysResRoleMasterStdDataSvc<TenantUserSysResRoleMasterVO, TenantUserSysResRoleDelParam> {
}
