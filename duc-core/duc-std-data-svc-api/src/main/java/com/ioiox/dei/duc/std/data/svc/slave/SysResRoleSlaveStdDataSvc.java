package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;

import java.util.List;

public interface SysResRoleSlaveStdDataSvc<
        R extends BaseRoleSlaveVO,
        QP extends RoleQueryParam> {

    int countByParam(final QP queryParam);
    List<R> queryByParam(final QP queryParam,
                         final SysResRoleQueryCfg queryCfg);
}
