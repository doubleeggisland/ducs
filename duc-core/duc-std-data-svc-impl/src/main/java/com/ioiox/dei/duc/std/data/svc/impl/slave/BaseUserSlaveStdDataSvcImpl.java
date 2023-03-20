package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.vo.std.slave.*;

import java.util.List;

public abstract class BaseUserSlaveStdDataSvcImpl<
        R extends RoleSlaveStdVO,
        RR extends SysResRoleSlaveStdVO,
        TR extends TmpRoleSlaveStdVO,
        TRR extends TmpSysResRoleSlaveStdVO,
        UG extends UserGrpSlaveStdVO<R, RR>,
        T extends UserSlaveStdVO<R, RR, TR, TRR, UG>,
        E extends BaseUser,
        QP extends UserQueryParam>
        extends BaseDeiSlaveStdDataSvc<T, E> {

    public int count(final QP queryParam) {
        return 0;
    }

    public List<T> query(final QP queryParam, final UserQueryCfg queryCfg) {
        return null;
    }

    protected void assembleCommonAttrs(final T stdVO, final E entity) {
        super.assembleCommonAttrs(stdVO, entity);
        stdVO.setUsername(entity.getUsername());
        stdVO.setNickName(entity.getNickName());
        stdVO.setMobile(entity.getMobile());
        stdVO.setEmail(entity.getEmail());
        stdVO.setStatus(entity.getStatus());
        stdVO.setPwd(entity.getPwd());
        stdVO.setAvatarUrl(entity.getAvatarUrl());
    }
}
