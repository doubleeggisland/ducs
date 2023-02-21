package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctTmpRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpRoleR2MenuSlaveDbSvc")
public class UserAcctTmpRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2Menu, UserAcctTmpRoleR2MenuSlaveMapper>
        implements UserAcctTmpRoleR2MenuSlaveDbSvc {

    @Autowired
    private UserAcctTmpRoleR2MenuSlaveMapper mapper;

    @Override
    protected UserAcctTmpRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色与菜单关联表";
    }
}
