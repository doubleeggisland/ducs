package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserGrpR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;

public class TenantUserGrpR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2SysResRole, TenantUserGrpR2SysResRoleMasterMapper>
        implements TenantUserGrpR2SysResRoleMasterDbSvc {

    @Autowired
    private TenantUserGrpR2SysResRoleMasterMapper mapper;

    @Override
    protected TenantUserGrpR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与系统资源角色关联表";
    }
}
