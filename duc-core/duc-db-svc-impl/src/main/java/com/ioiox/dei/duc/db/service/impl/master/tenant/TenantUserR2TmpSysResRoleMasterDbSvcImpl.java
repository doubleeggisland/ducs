package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserR2TmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserR2TmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserR2TmpSysResRoleMasterDbSvc")
public class TenantUserR2TmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserR2SysResRole, TenantUserR2TmpSysResRoleMasterMapper>
        implements TenantUserR2TmpSysResRoleMasterDbSvc {

    @Autowired
    private TenantUserR2TmpSysResRoleMasterMapper mapper;

    @Override
    protected TenantUserR2TmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与临时系统资源角色关联表";
    }
}