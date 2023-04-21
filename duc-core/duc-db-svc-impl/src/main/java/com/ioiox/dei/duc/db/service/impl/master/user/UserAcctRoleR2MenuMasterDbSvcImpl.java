package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctRoleR2MenuMasterDbSvc")
public class UserAcctRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, UserAcctRoleR2MenuMasterMapper>
        implements UserAcctRoleR2MenuMasterDbSvc {

    @Autowired
    private UserAcctRoleR2MenuMasterMapper mapper;

    @Override
    public int save(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2Menu.instances(menuSids, roleSid, operator, operateTime));
    }

    @Override
    protected UserAcctRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与菜单关联表";
    }
}
