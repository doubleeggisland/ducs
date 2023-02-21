package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserGrpR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserGrpR2SysResRoleSlaveDbSvc")
public class AcctUserGrpR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserGrpR2SysResRole, AcctUserGrpR2SysResRoleSlaveMapper>
        implements AcctUserGrpR2SysResRoleSlaveDbSvc {

    @Autowired
    private AcctUserGrpR2SysResRoleSlaveMapper mapper;

    @Override
    protected AcctUserGrpR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与系统资源角色关系表";
    }
}
