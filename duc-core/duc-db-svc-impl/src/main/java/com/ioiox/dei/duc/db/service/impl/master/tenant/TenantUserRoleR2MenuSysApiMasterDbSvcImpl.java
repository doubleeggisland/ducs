package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserRoleR2MenuSysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleR2MenuSysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserRoleR2MenuSysApiMasterDbSvc")
public class TenantUserRoleR2MenuSysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2MenuSysApi, TenantUserRoleR2MenuSysApiMasterMapper>
        implements TenantUserRoleR2MenuSysApiMasterDbSvc {

    @Autowired
    private TenantUserRoleR2MenuSysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2MenuSysApi.instances(sysApiMappingSids, roleSid, operator, operateTime));
    }

    @Override
    protected TenantUserRoleR2MenuSysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色与菜单相关接口关联表";
    }
}
