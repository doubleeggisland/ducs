package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserR2SysResRoleMasterDbSvc")
public class TenantUserR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserR2SysResRole, TenantUserR2SysResRoleMasterMapper>
        implements TenantUserR2SysResRoleMasterDbSvc {

    @Autowired
    private TenantUserR2SysResRoleMasterMapper mapper;

    @Override
    protected TenantUserR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与系统资源角色关联表";
    }
}
