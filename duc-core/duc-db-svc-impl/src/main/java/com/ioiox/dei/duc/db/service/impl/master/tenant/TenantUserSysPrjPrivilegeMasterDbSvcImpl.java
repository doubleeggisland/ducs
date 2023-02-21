package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserSysPrjPrivilegeMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysPrjPrivilegeMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserSysPrjPrivilegeMasterDbSvc")
public class TenantUserSysPrjPrivilegeMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserSysPrjPrivilege, TenantUserSysPrjPrivilegeMasterMapper>
        implements TenantUserSysPrjPrivilegeMasterDbSvc {

    @Autowired
    private TenantUserSysPrjPrivilegeMasterMapper mapper;

    @Override
    protected TenantUserSysPrjPrivilegeMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统项目权限表";
    }
}
