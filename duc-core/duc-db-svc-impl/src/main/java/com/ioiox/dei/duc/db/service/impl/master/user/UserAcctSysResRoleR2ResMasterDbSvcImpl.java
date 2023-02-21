package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctSysResRoleR2ResMasterMapper;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctSysResRoleR2ResMasterDbSvc")
public class UserAcctSysResRoleR2ResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysResRoleR2Res, UserAcctSysResRoleR2ResMasterMapper>
        implements UserAcctSysResRoleR2ResMasterDbSvc {

    @Autowired
    private UserAcctSysResRoleR2ResMasterMapper mapper;

    @Override
    protected UserAcctSysResRoleR2ResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统资源角色与资源关联表";
    }
}
