package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.AcctUserGrp;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserGrpSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserGrpSlaveDbSvc")
public class AcctUserGrpSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<AcctUserGrp, AcctUserGrpSlaveMapper>
        implements AcctUserGrpSlaveDbSvc {

    @Autowired
    private AcctUserGrpSlaveMapper mapper;

    @Override
    protected AcctUserGrpSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组表";
    }
}
