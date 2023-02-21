package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctRoleR2MenuSlaveDbSvc")
public class UserAcctRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2Menu, UserAcctRoleR2MenuSlaveMapper>
        implements UserAcctRoleR2MenuSlaveDbSvc {

    @Autowired
    private UserAcctRoleR2MenuSlaveMapper mapper;

    @Override
    protected UserAcctRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与菜单关联表";
    }
}
