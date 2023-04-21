package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.*;

import java.util.List;

public interface UserGrpSlaveStdDataSvc<
        R extends RoleSlaveStdVO,
        RR extends SysResRoleSlaveStdVO,
        S extends UserGrpSlaveStdVO<R, RR>,
        QP extends UserGrpQueryParam> {

    int countByParam(final QP queryParam);
    List<S> queryByParam(final QP queryParam, final UserGrpQueryCfg queryCfg);
}