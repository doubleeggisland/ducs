package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpRoleR2MenuSysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpRoleR2MenuSysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctTmpRoleR2MenuSysApiMasterDbSvc")
public class UserAcctTmpRoleR2MenuSysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2MenuSysApi, UserAcctTmpRoleR2MenuSysApiMasterMapper>
        implements UserAcctTmpRoleR2MenuSysApiMasterDbSvc {

    @Autowired
    private UserAcctTmpRoleR2MenuSysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiMappingSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2MenuSysApi.instances(sysApiMappingSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected UserAcctTmpRoleR2MenuSysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色与菜单相关接口关联表";
    }
}
