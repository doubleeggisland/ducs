package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("tenantUserR2RoleMasterDbSvc")
public class TenantUserR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserR2Role, TenantUserR2RoleMasterMapper>
        implements TenantUserR2RoleMasterDbSvc {

    @Autowired
    private TenantUserR2RoleMasterMapper mapper;

    @Override
    protected TenantUserR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与角色关联表";
    }
}
