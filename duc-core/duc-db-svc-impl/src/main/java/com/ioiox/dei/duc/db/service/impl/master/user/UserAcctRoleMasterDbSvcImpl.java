package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctRoleMasterDbSvc")
public class UserAcctRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctRole, UserAcctRoleMasterMapper>
        implements UserAcctRoleMasterDbSvc {

    @Autowired
    private UserAcctRoleMasterMapper mapper;

    @Override
    protected UserAcctRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色表";
    }
}
