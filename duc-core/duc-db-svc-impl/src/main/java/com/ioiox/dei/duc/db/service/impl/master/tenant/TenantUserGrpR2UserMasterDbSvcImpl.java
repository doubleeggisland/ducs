package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserGrpR2UserMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpR2UserMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserGrpR2UserMasterDbSvc")
public class TenantUserGrpR2UserMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2User, TenantUserGrpR2UserMasterMapper>
        implements TenantUserGrpR2UserMasterDbSvc {

    @Autowired
    private TenantUserGrpR2UserMasterMapper mapper;

    @Override
    public int save(final List<Long> userGrpSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return dbInsert(UserGrpR2User.instances(userGrpSids, tenantUserSid, operator, operateTime));
    }

    @Override
    protected TenantUserGrpR2UserMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与用户关联表";
    }
}
