package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserGrpR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserGrpR2SysResRoleMasterDbSvc")
public class TenantUserGrpR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2SysResRole, TenantUserGrpR2SysResRoleMasterMapper>
        implements TenantUserGrpR2SysResRoleMasterDbSvc {

    @Autowired
    private TenantUserGrpR2SysResRoleMasterMapper mapper;

    @Override
    public int save(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return dbInsert(UserGrpR2SysResRole.instances(sysResRoleSids, userGrpSid, operator, operateTime));
    }

    @Override
    protected TenantUserGrpR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与系统资源角色关联表";
    }
}
