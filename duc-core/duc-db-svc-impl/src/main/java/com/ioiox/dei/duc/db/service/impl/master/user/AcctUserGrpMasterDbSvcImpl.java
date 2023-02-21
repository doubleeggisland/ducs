package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.AcctUserGrp;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserGrpMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserGrpMasterDbSvc")
public class AcctUserGrpMasterDbSvcImpl
        extends BaseDeiMasterDbService<AcctUserGrp, AcctUserGrpMasterMapper>
        implements AcctUserGrpMasterDbSvc {

    @Autowired
    private AcctUserGrpMasterMapper mapper;

    @Override
    protected AcctUserGrpMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组表";
    }
}
