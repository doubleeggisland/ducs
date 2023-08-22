package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2TmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2TmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctR2TmpRoleMasterDbSvc")
public class UserAcctR2TmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2Role, UserAcctR2TmpRoleMasterMapper>
        implements UserAcctR2TmpRoleMasterDbSvc {

    @Autowired
    private UserAcctR2TmpRoleMasterMapper mapper;

    @Override
    public int save(final List<Long> tmpRoleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return dbInsert(UserAcctR2Role.instances(tmpRoleSids, userAcctSid, operator, operateTime));
    }

    @Override
    protected UserAcctR2TmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与临时角色关联表";
    }
}
