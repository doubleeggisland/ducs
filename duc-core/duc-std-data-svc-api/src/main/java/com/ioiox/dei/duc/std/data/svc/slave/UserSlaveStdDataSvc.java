package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.*;

import java.util.List;

public interface UserSlaveStdDataSvc<
        R extends RoleSlaveStdVO,
        RR extends SysResRoleSlaveStdVO,
        TR extends TmpRoleSlaveStdVO,
        TRR extends TmpSysResRoleSlaveStdVO,
        UG extends UserGrpSlaveStdVO<R, RR>,
        T extends UserSlaveStdVO<R, RR, TR, TRR, UG>,
        QP extends UserQueryParam> {
    int countByParam(final QP queryParam);
    List<T> queryByParam(final QP queryParam, final UserQueryCfg queryCfg);
}
