package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctRoleR2MenuMasterDbSvc")
public class UserAcctRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, UserAcctRoleR2MenuMasterMapper>
        implements UserAcctRoleR2MenuMasterDbSvc {

    @Autowired
    private UserAcctRoleR2MenuMasterMapper mapper;

    @Override
    protected UserAcctRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与菜单关联表";
    }
}
