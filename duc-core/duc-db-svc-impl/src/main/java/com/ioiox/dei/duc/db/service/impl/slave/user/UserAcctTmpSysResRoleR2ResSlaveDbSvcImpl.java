package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctTmpSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpSysResRoleR2ResSlaveDbSvc")
public class UserAcctTmpSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysResRoleR2Res, UserAcctTmpSysResRoleR2ResSlaveMapper>
        implements UserAcctTmpSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private UserAcctTmpSysResRoleR2ResSlaveMapper mapper;

    @Override
    protected UserAcctTmpSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时系统资源资源与资源关联表";
    }
}
