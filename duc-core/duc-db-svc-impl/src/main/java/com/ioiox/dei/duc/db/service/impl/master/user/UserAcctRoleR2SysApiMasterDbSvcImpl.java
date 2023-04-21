package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctRoleR2SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctRoleR2SysApiMasterDbSvc")
public class UserAcctRoleR2SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2SysApi, UserAcctRoleR2SysApiMasterMapper>
        implements UserAcctRoleR2SysApiMasterDbSvc {

    @Autowired
    private UserAcctRoleR2SysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiSids, final Long roleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2SysApi.instances(sysApiSids, roleSid, operator, operateTime));
    }

    @Override
    protected UserAcctRoleR2SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与系统接口关联表";
    }
}
