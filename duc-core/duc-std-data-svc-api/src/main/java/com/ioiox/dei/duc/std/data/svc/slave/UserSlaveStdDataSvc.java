package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.*;

import java.util.List;

public interface UserSlaveStdDataSvc<
        R extends RoleSlaveVO,
        RR extends SysResRoleSlaveVO,
        TR extends TmpRoleSlaveVO,
        TRR extends TmpSysResRoleSlaveVO,
        UG extends UserGrpSlaveVO<R, RR>,
        T extends UserSlaveVO<R, RR, TR, TRR, UG>,
        QP extends UserQueryParam> {
    int countByParam(final QP queryParam);
    List<T> queryByParam(final QP queryParam, final UserQueryCfg queryCfg);
}
