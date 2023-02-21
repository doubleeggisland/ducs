package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctTmpRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpRoleR2SysApiSlaveDbSvc")
public class UserAcctTmpRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2SysApi, UserAcctTmpRoleR2SysApiSlaveMapper>
        implements UserAcctTmpRoleR2SysApiSlaveDbSvc {

    @Autowired
    private UserAcctTmpRoleR2SysApiSlaveMapper mapper;

    @Override
    protected UserAcctTmpRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色与系统API关联表";
    }
}
