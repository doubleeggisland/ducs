package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserSysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserSysResRoleMasterDbSvc")
public class TenantUserSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserSysResRole, TenantUserSysResRoleMasterMapper>
        implements TenantUserSysResRoleMasterDbSvc {

    @Autowired
    private TenantUserSysResRoleMasterMapper mapper;

    @Override
    protected TenantUserSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统资源角色表";
    }
}
