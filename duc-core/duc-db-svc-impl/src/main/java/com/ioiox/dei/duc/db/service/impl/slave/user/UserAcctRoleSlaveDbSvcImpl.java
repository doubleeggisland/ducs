package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctRoleSlaveDbSvc")
public class UserAcctRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctRole, UserAcctRoleSlaveMapper>
        implements UserAcctRoleSlaveDbSvc {

    @Autowired
    private UserAcctRoleSlaveMapper mapper;

    @Override
    protected UserAcctRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色表";
    }
}
