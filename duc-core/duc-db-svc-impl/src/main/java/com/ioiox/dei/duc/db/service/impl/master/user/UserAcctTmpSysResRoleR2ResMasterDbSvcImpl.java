package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpSysResRoleR2ResMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctTmpSysResRoleR2ResMasterDbSvc")
public class UserAcctTmpSysResRoleR2ResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysResRoleR2Res, UserAcctTmpSysResRoleR2ResMasterMapper>
        implements UserAcctTmpSysResRoleR2ResMasterDbSvc {

    @Autowired
    private UserAcctTmpSysResRoleR2ResMasterMapper mapper;

    @Override
    protected UserAcctTmpSysResRoleR2ResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时系统资源资源与资源关联表";
    }
}
