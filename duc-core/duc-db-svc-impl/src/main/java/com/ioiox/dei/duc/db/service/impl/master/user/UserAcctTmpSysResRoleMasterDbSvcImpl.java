package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpSysResRoleMasterDbSvc")
public class UserAcctTmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctTmpSysResRole, UserAcctTmpSysResRoleMasterMapper>
        implements UserAcctTmpSysResRoleMasterDbSvc {

    @Autowired
    private UserAcctTmpSysResRoleMasterMapper mapper;

    @Override
    protected UserAcctTmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时系统资源角色表";
    }
}
