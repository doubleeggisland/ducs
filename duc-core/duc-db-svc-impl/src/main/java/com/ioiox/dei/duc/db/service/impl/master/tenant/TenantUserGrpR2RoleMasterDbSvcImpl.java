package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserGrpR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserGrpR2RoleMasterDbSvc")
public class TenantUserGrpR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2Role, TenantUserGrpR2RoleMasterMapper>
        implements TenantUserGrpR2RoleMasterDbSvc {

    @Autowired
    private TenantUserGrpR2RoleMasterMapper mapper;

    @Override
    protected TenantUserGrpR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与角色关联表";
    }
}
