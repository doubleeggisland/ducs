package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpSysResRoleR2ResSlaveDbSvc")
public class TenantUserTmpSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysResRoleR2Res, TenantUserTmpSysResRoleR2ResSlaveMapper>
        implements TenantUserTmpSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private TenantUserTmpSysResRoleR2ResSlaveMapper mapper;

    @Override
    protected TenantUserTmpSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时系统资源角色与资源关联表";
    }
}
