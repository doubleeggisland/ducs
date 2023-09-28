package com.ioiox.dei.duc.std.data.svc.master.tenant;

import com.ioiox.dei.duc.beans.model.master.tenant.TenantDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantMasterVO;

public interface TenantMasterStdDataSvc {

    long save(final TenantMasterVO tenant);

    boolean update(final TenantMasterVO tenant);

    int remove(final TenantDelParam delParam);
}
