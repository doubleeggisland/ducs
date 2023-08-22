package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctRoleR2MenuSysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuSysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctRoleR2MenuSysApiMasterDbSvc")
public class UserAcctRoleR2MenuSysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2MenuSysApi, UserAcctRoleR2MenuSysApiMasterMapper>
        implements UserAcctRoleR2MenuSysApiMasterDbSvc {

    @Autowired
    private UserAcctRoleR2MenuSysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2MenuSysApi.instances(sysApiMappingSids, roleSid, operator, operateTime));
    }

    @Override
    protected UserAcctRoleR2MenuSysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与菜单相关接口关联表";
    }
}
