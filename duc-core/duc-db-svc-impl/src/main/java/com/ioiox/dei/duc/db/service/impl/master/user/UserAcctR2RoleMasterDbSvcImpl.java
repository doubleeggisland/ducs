package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2RoleMasterDbSvc")
public class UserAcctR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2Role, UserAcctR2RoleMasterMapper>
        implements UserAcctR2RoleMasterDbSvc {

    @Autowired
    private UserAcctR2RoleMasterMapper mapper;

    @Override
    protected UserAcctR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与角色关联表";
    }
}
