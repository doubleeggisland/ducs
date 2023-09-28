package com.ioiox.dei.duc.std.data.svc.master.tenant;

import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.UserMasterStdDataSvc;

public interface TenantUserMasterStdDataSvc
        extends UserMasterStdDataSvc<TenantUserMasterVO, TenantUserDelParam> {
}
