package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2SysResRoleMasterDbSvc")
public class UserAcctR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2SysResRole, UserAcctR2SysResRoleMasterMapper>
        implements UserAcctR2SysResRoleMasterDbSvc {

    @Autowired
    private UserAcctR2SysResRoleMasterMapper mapper;

    @Override
    protected UserAcctR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与系统资源角色关系表";
    }
}
