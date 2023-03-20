package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcct;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAcctMasterDbSvc")
public class UserAcctMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcct, UserAcctMasterMapper>
        implements UserAcctMasterDbSvc {

    @Autowired
    private UserAcctMasterMapper mapper;

    @Override
    protected UserAcctMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户账号表";
    }
}
