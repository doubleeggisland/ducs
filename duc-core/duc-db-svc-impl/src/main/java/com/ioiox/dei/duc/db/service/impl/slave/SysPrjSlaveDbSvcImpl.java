package com.ioiox.dei.duc.db.service.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysPrj;
import com.ioiox.dei.duc.db.mapper.slave.SysPrjSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.SysPrjSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysPrjSlaveDbSvc")
public class SysPrjSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysPrj, SysPrjSlaveMapper>
        implements SysPrjSlaveDbSvc {

    @Autowired
    private SysPrjSlaveMapper mapper;

    @Override
    protected SysPrjSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "系统项目信息表";
    }
}
