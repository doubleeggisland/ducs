package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserRoleR2MenuMasterDbSvc")
public class TenantUserRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, TenantUserRoleR2MenuMasterMapper>
        implements TenantUserRoleR2MenuMasterDbSvc {

    @Autowired
    private TenantUserRoleR2MenuMasterMapper mapper;

    @Override
    protected TenantUserRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色与菜单关联表";
    }
}
