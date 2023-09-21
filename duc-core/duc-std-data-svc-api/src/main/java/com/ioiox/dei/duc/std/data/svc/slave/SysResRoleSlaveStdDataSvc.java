package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.SimpleRoleSlaveVO;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;

import java.util.List;

public interface SysResRoleSlaveStdDataSvc<
        R extends SimpleRoleSlaveVO,
        QP extends SimpleRoleQueryParam> {

    int countByParam(final QP queryParam);
    List<R> queryByParam(final QP queryParam,
                         final SysResRoleQueryCfg queryCfg);
}
