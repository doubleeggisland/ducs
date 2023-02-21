package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserGrpR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserGrpR2RoleSlaveDbSvc")
public class AcctUserGrpR2RoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserGrpR2Role, AcctUserGrpR2RoleSlaveMapper>
        implements AcctUserGrpR2RoleSlaveDbSvc {

    @Autowired
    private AcctUserGrpR2RoleSlaveMapper mapper;

    @Override
    protected AcctUserGrpR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与角色关联表";
    }
}
