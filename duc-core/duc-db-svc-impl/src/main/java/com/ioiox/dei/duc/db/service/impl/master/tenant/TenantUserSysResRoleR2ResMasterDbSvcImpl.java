package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserSysResRoleR2ResMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserSysResRoleR2ResMasterDbSvc")
public class TenantUserSysResRoleR2ResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysResRoleR2Res, TenantUserSysResRoleR2ResMasterMapper>
        implements TenantUserSysResRoleR2ResMasterDbSvc {

    @Autowired
    private TenantUserSysResRoleR2ResMasterMapper mapper;

    @Override
    public int save(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime) {
        return dbInsert(SysResRoleR2Res.instances(sysResSids, sysResRoleSid, operator, operateTime));
    }

    @Override
    protected TenantUserSysResRoleR2ResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统资源角色与资源关联表";
    }
}
