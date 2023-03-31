package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryParam;

import java.util.List;

public interface RoleSlaveStdDataSvc<
        R extends BaseRoleSlaveStdVO,
        QP extends RoleQueryParam> {
    int countByParam(final QP queryParam);
    List<R> queryByParam(final QP queryParam, final RoleQueryCfg queryCfg);
}
