package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleQueryCfg;

import java.util.List;

public interface SysResRoleSlaveStdDataSvc<
        R extends BaseRoleSlaveStdVO,
        QP extends RoleQueryParam> {

    int countByParam(final QP queryParam);
    List<R> queryByParam(final QP queryParam,
                         final SysResRoleQueryCfg queryCfg);
}
