package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctR2RoleMasterDbSvc")
public class UserAcctR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2Role, UserAcctR2RoleMasterMapper>
        implements UserAcctR2RoleMasterDbSvc {

    @Autowired
    private UserAcctR2RoleMasterMapper mapper;

    @Override
    public int save(final List<Long> roleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return dbInsert(UserAcctR2Role.instances(roleSids, userAcctSid, operator, operateTime));
    }

    @Override
    protected UserAcctR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与角色关联表";
    }
}
