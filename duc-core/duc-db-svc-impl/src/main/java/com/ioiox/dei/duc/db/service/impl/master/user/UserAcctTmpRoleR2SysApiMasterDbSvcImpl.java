package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpRoleR2SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpRoleR2SysApiMasterDbSvc")
public class UserAcctTmpRoleR2SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2SysApi, UserAcctTmpRoleR2SysApiMasterMapper>
        implements UserAcctTmpRoleR2SysApiMasterDbSvc {

    @Autowired
    private UserAcctTmpRoleR2SysApiMasterMapper mapper;

    @Override
    protected UserAcctTmpRoleR2SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色与系统API关联表";
    }
}
