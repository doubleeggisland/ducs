package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpSysResRoleR2ResMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpSysResRoleR2ResMasterDbSvc")
public class TenantUserTmpSysResRoleR2ResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysResRoleR2Res, TenantUserTmpSysResRoleR2ResMasterMapper>
        implements TenantUserTmpSysResRoleR2ResMasterDbSvc {

    @Autowired
    private TenantUserTmpSysResRoleR2ResMasterMapper mapper;

    @Override
    protected TenantUserTmpSysResRoleR2ResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时系统资源角色与资源关联表";
    }
}
