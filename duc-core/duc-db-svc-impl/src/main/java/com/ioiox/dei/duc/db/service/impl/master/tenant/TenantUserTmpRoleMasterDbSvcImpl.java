package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpRoleMasterDbSvc")
public class TenantUserTmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserTmpRole, TenantUserTmpRoleMasterMapper>
        implements TenantUserTmpRoleMasterDbSvc {

    @Autowired
    private TenantUserTmpRoleMasterMapper mapper;

    @Override
    protected TenantUserTmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色表";
    }
}
