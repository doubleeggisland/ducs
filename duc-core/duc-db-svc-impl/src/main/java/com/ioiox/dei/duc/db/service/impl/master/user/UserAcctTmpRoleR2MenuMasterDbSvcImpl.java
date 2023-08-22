package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctTmpRoleR2MenuMasterDbSvc")
public class UserAcctTmpRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, UserAcctTmpRoleR2MenuMasterMapper>
        implements UserAcctTmpRoleR2MenuMasterDbSvc {

    @Autowired
    private UserAcctTmpRoleR2MenuMasterMapper mapper;

    @Override
    public int save(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2Menu.instances(menuSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected UserAcctTmpRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色与菜单关联表";
    }
}
