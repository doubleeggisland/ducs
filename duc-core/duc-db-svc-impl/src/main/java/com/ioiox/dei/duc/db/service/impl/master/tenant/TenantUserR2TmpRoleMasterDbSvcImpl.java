package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserR2TmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserR2TmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserR2TmpRoleMasterDbSvc")
public class TenantUserR2TmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserR2Role, TenantUserR2TmpRoleMasterMapper>
        implements TenantUserR2TmpRoleMasterDbSvc {

    @Autowired
    private TenantUserR2TmpRoleMasterMapper mapper;

    @Override
    public int save(final List<Long> tmpRoleSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return dbInsert(TenantUserR2Role.instances(tmpRoleSids, tenantUserSid, operator, operateTime));
    }

    @Override
    protected TenantUserR2TmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与临时角色关联表";
    }
}
