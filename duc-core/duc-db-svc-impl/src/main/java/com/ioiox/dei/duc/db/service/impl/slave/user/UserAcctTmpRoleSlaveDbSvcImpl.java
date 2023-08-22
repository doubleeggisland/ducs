package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctTmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpRoleSlaveDbSvc")
public class UserAcctTmpRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctTmpRole, UserAcctTmpRoleSlaveMapper>
        implements UserAcctTmpRoleSlaveDbSvc {

    @Autowired
    private UserAcctTmpRoleSlaveMapper mapper;

    @Override
    protected UserAcctTmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色表";
    }
}
