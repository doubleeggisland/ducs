package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserSysPrjPrivilegeMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserSysPrjPrivilegeMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserSysPrjPrivilegeMasterDbSvc")
public class AcctUserSysPrjPrivilegeMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserSysPrjPrivilege, AcctUserSysPrjPrivilegeMasterMapper>
        implements AcctUserSysPrjPrivilegeMasterDbSvc {

    @Autowired
    private AcctUserSysPrjPrivilegeMasterMapper mapper;

    @Override
    protected AcctUserSysPrjPrivilegeMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统项目权限表";
    }
}
