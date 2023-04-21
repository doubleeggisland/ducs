package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserTmpRoleR2MenuMasterDbSvc")
public class TenantUserTmpRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, TenantUserTmpRoleR2MenuMasterMapper>
        implements TenantUserTmpRoleR2MenuMasterDbSvc {

    @Autowired
    private TenantUserTmpRoleR2MenuMasterMapper mapper;

    @Override
    public int save(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2Menu.instances(menuSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected TenantUserTmpRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色与菜单关联表";
    }
}
