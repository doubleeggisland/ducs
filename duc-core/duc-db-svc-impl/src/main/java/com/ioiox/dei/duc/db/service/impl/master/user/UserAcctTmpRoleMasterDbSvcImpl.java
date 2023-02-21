package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpRoleMasterDbSvc")
public class UserAcctTmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctTmpRole, UserAcctTmpRoleMasterMapper>
        implements UserAcctTmpRoleMasterDbSvc {

    @Autowired
    private UserAcctTmpRoleMasterMapper mapper;

    @Override
    protected UserAcctTmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色表";
    }
}
