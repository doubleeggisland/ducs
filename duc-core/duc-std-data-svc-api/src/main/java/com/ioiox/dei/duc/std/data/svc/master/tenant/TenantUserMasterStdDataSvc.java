package com.ioiox.dei.duc.std.data.svc.master.tenant;

import com.ioiox.dei.duc.beans.model.master.user.UserAcctDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.UserMasterStdDataSvc;

public interface TenantUserMasterStdDataSvc
        extends UserMasterStdDataSvc<UserAcctMasterVO, UserAcctDelParam> {
}
