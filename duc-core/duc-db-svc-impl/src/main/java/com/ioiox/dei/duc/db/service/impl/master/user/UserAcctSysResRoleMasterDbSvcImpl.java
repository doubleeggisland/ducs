package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctSysResRoleMasterDbSvc")
public class UserAcctSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctSysResRole, UserAcctSysResRoleMasterMapper>
        implements UserAcctSysResRoleMasterDbSvc {

    @Autowired
    private UserAcctSysResRoleMasterMapper mapper;

    @Override
    protected UserAcctSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统资源角色";
    }
}
