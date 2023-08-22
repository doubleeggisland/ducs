package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpRoleR2MenuSysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpRoleR2MenuSysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserTmpRoleR2MenuSysApiMasterDbSvc")
public class TenantUserTmpRoleR2MenuSysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2MenuSysApi, TenantUserTmpRoleR2MenuSysApiMasterMapper>
        implements TenantUserTmpRoleR2MenuSysApiMasterDbSvc {

    @Autowired
    private TenantUserTmpRoleR2MenuSysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiMappingSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2MenuSysApi.instances(sysApiMappingSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected TenantUserTmpRoleR2MenuSysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色与菜单相关接口关联表";
    }
}
