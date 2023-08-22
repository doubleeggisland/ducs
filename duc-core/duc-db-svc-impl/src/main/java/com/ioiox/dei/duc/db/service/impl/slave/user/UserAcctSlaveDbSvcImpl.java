package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcct;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctSlaveDbSvc")
public class UserAcctSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserAcct, UserAcctSlaveMapper>
        implements UserAcctSlaveDbSvc {

    @Autowired
    private UserAcctSlaveMapper mapper;

    @Override
    protected UserAcctSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户账号表";
    }
}
