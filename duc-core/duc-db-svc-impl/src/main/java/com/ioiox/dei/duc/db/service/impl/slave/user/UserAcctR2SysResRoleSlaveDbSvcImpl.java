package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2SysResRoleSlaveDbSvc")
public class UserAcctR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctR2SysResRole, UserAcctR2SysResRoleSlaveMapper>
        implements UserAcctR2SysResRoleSlaveDbSvc {

    @Autowired
    private UserAcctR2SysResRoleSlaveMapper mapper;

    @Override
    protected UserAcctR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与系统资源角色关系表";
    }
}
