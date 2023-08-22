package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctSysResRoleSlaveDbSvc")
public class UserAcctSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctSysResRole, UserAcctSysResRoleSlaveMapper>
        implements UserAcctSysResRoleSlaveDbSvc {

    @Autowired
    private UserAcctSysResRoleSlaveMapper mapper;

    @Override
    protected UserAcctSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统资源角色";
    }
}
