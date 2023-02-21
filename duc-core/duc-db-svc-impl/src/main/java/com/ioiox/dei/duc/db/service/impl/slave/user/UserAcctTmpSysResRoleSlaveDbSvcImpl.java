package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctTmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpSysResRoleSlaveDbSvc")
public class UserAcctTmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctTmpSysResRole, UserAcctTmpSysResRoleSlaveMapper>
        implements UserAcctTmpSysResRoleSlaveDbSvc {

    @Autowired
    private UserAcctTmpSysResRoleSlaveMapper mapper;

    @Override
    protected UserAcctTmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时系统资源角色表";
    }
}
