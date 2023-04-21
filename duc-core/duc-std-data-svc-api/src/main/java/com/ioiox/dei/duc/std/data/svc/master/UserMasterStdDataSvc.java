package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.vo.std.master.UserDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserMasterStdVO;

public interface UserMasterStdDataSvc<T extends UserMasterStdVO, D extends UserDelParam> {
    Long save(final T user);
    boolean update(final T user);
    int remove(final D delParam);
}
