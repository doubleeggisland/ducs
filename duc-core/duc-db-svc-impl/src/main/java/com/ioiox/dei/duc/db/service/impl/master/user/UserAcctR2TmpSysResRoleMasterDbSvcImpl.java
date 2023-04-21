package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2TmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2TmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctR2TmpSysResRoleMasterDbSvc")
public class UserAcctR2TmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2SysResRole, UserAcctR2TmpSysResRoleMasterMapper>
        implements UserAcctR2TmpSysResRoleMasterDbSvc {

    @Autowired
    private UserAcctR2TmpSysResRoleMasterMapper mapper;

    @Override
    public int save(final List<Long> tmpSysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return dbInsert(UserAcctR2SysResRole.instances(tmpSysResRoleSids, userAcctSid, operator, operateTime));
    }

    @Override
    protected UserAcctR2TmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与临时系统资源角色关联表";
    }
}
