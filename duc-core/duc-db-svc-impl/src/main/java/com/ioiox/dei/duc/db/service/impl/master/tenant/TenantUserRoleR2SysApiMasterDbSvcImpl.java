package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserRoleR2SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserRoleR2SysApiMasterDbSvc")
public class TenantUserRoleR2SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2SysApi, TenantUserRoleR2SysApiMasterMapper>
        implements TenantUserRoleR2SysApiMasterDbSvc {

    @Autowired
    private TenantUserRoleR2SysApiMasterMapper mapper;

    @Override
    protected TenantUserRoleR2SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色与系统API关联表";
    }
}
