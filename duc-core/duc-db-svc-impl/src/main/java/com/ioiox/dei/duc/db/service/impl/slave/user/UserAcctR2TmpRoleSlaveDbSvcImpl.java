package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2TmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctR2TmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2TmpRoleSlaveDbSvc")
public class UserAcctR2TmpRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctR2Role, UserAcctR2TmpRoleSlaveMapper>
        implements UserAcctR2TmpRoleSlaveDbSvc {

    @Autowired
    private UserAcctR2TmpRoleSlaveMapper mapper;

    @Override
    protected UserAcctR2TmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与临时角色关联表";
    }
}
