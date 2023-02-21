package com.ioiox.dei.duc.db.service.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysPrj;
import com.ioiox.dei.duc.db.mapper.master.SysPrjMasterMapper;
import com.ioiox.dei.duc.db.service.master.SysPrjMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysPrjMasterDbSvc")
public class SysPrjMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysPrj, SysPrjMasterMapper>
        implements SysPrjMasterDbSvc {

    @Autowired
    private SysPrjMasterMapper mapper;

    @Override
    protected SysPrjMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "系统项目信息表";
    }
}
