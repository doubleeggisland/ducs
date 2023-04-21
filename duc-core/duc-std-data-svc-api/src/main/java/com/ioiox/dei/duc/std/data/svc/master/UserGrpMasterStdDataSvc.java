package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.vo.std.master.UserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserGrpMasterStdVO;

public interface UserGrpMasterStdDataSvc<T extends UserGrpMasterStdVO, D extends UserGrpDelParam> {
    Long save(final T userGrp);
    boolean update(final T userGrp);
    int remove(final D delParam);
}
