package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserSysPrjPrivilegeSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserSysPrjPrivilegeSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserSysPrjPrivilegeSlaveDbSvc")
public class AcctUserSysPrjPrivilegeSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserSysPrjPrivilege, AcctUserSysPrjPrivilegeSlaveMapper>
        implements AcctUserSysPrjPrivilegeSlaveDbSvc {

    @Autowired
    private AcctUserSysPrjPrivilegeSlaveMapper mapper;

    @Override
    protected AcctUserSysPrjPrivilegeSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统项目权限表";
    }
}
