package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryParam;

import java.util.List;

public interface RoleSlaveStdDataSvc<
        R extends BaseRoleSlaveVO,
        QP extends RoleQueryParam> {
    int countByParam(final QP queryParam);
    List<R> queryByParam(final QP queryParam, final RoleQueryCfg queryCfg);
}
