package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2TmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctR2TmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2TmpSysResRoleSlaveDbSvc")
public class UserAcctR2TmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcctR2SysResRole, UserAcctR2TmpSysResRoleSlaveMapper>
        implements UserAcctR2TmpSysResRoleSlaveDbSvc {

    @Autowired
    private UserAcctR2TmpSysResRoleSlaveMapper mapper;

    @Override
    protected UserAcctR2TmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与临时系统资源角色关联表";
    }
}
