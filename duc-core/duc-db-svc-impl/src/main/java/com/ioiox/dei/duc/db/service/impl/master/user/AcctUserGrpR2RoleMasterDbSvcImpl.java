package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserGrpR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("acctUserGrpR2RoleMasterDbSvc")
public class AcctUserGrpR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2Role, AcctUserGrpR2RoleMasterMapper>
        implements AcctUserGrpR2RoleMasterDbSvc {

    @Autowired
    private AcctUserGrpR2RoleMasterMapper mapper;

    @Override
    public int save(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return dbInsert(UserGrpR2Role.instances(roleSids, userGrpSid, operator, operateTime));
    }

    @Override
    protected AcctUserGrpR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与角色关联表";
    }
}
