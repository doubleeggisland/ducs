package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctSysResRoleR2ResSlaveDbSvc")
public class UserAcctSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysResRoleR2Res, UserAcctSysResRoleR2ResSlaveMapper>
        implements UserAcctSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private UserAcctSysResRoleR2ResSlaveMapper mapper;

    @Override
    protected UserAcctSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统资源角色与资源关联表";
    }
}
