package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserGrpR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserGrpR2SysResRoleMasterDbSvc")
public class AcctUserGrpR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2SysResRole, AcctUserGrpR2SysResRoleMasterMapper>
        implements AcctUserGrpR2SysResRoleMasterDbSvc {

    @Autowired
    private AcctUserGrpR2SysResRoleMasterMapper mapper;

    @Override
    protected AcctUserGrpR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与系统资源角色关系表";
    }
}
