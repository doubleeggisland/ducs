package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserSysPrjPrivilegeSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysPrjPrivilegeSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserSysPrjPrivilegeSlaveDbSvc")
public class TenantUserSysPrjPrivilegeSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserSysPrjPrivilege, TenantUserSysPrjPrivilegeSlaveMapper>
        implements TenantUserSysPrjPrivilegeSlaveDbSvc {

    @Autowired
    private TenantUserSysPrjPrivilegeSlaveMapper mapper;

    @Override
    protected TenantUserSysPrjPrivilegeSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统项目权限表";
    }
}
