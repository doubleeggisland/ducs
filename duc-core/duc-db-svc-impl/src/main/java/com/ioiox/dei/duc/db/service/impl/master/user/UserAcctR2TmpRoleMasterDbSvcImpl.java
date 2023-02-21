package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2TmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2TmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2TmpRoleMasterDbSvc")
public class UserAcctR2TmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2Role, UserAcctR2TmpRoleMasterMapper>
        implements UserAcctR2TmpRoleMasterDbSvc {

    @Autowired
    private UserAcctR2TmpRoleMasterMapper mapper;

    @Override
    protected UserAcctR2TmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与临时角色关联表";
    }
}
