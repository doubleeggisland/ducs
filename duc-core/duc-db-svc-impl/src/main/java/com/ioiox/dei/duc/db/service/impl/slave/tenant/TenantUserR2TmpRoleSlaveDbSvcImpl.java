package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2TmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2TmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserR2TmpRoleSlaveDbSvc")
public class TenantUserR2TmpRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserR2Role, TenantUserR2TmpRoleSlaveMapper>
        implements TenantUserR2TmpRoleSlaveDbSvc {

    @Autowired
    private TenantUserR2TmpRoleSlaveMapper mapper;

    @Override
    protected TenantUserR2TmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与临时角色关联表";
    }
}
