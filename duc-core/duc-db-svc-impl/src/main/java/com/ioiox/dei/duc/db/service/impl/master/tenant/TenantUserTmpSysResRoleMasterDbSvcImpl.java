package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpSysResRoleMasterDbSvc")
public class TenantUserTmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserTmpSysResRole, TenantUserTmpSysResRoleMasterMapper>
        implements TenantUserTmpSysResRoleMasterDbSvc {

    @Autowired
    private TenantUserTmpSysResRoleMasterMapper mapper;

    @Override
    protected TenantUserTmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时系统资源角色表";
    }
}
