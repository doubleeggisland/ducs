package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpRoleR2SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserTmpRoleR2SysApiMasterDbSvc")
public class TenantUserTmpRoleR2SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2SysApi, TenantUserTmpRoleR2SysApiMasterMapper>
        implements TenantUserTmpRoleR2SysApiMasterDbSvc {

    @Autowired
    private TenantUserTmpRoleR2SysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2SysApi.instances(sysApiSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected TenantUserTmpRoleR2SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色与系统接口关联表";
    }
}
