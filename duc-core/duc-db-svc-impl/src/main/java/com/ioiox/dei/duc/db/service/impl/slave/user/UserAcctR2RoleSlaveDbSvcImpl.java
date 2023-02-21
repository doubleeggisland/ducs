package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2RoleSlaveDbSvc")
public class UserAcctR2RoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctR2Role, UserAcctR2RoleSlaveMapper>
        implements UserAcctR2RoleSlaveDbSvc {

    @Autowired
    private UserAcctR2RoleSlaveMapper mapper;

    @Override
    protected UserAcctR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与角色关联表";
    }
}
