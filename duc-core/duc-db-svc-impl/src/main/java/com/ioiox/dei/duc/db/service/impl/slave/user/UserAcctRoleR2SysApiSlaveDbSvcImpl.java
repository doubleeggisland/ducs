package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctRoleR2SysApiSlaveDbSvc")
public class UserAcctRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2SysApi, UserAcctRoleR2SysApiSlaveMapper>
        implements UserAcctRoleR2SysApiSlaveDbSvc {

    @Autowired
    private UserAcctRoleR2SysApiSlaveMapper mapper;

    @Override
    protected UserAcctRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与系统API关联表";
    }
}
