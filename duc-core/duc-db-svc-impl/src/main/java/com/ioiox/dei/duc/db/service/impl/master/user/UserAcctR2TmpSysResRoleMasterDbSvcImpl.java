package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctR2TmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctR2TmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctR2TmpSysResRoleMasterDbSvc")
public class UserAcctR2TmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctR2SysResRole, UserAcctR2TmpSysResRoleMasterMapper>
        implements UserAcctR2TmpSysResRoleMasterDbSvc {

    @Autowired
    private UserAcctR2TmpSysResRoleMasterMapper mapper;

    @Override
    protected UserAcctR2TmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与临时系统资源角色关联表";
    }
}
