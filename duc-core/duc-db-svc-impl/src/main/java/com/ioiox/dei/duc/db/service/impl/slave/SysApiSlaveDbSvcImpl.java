package com.ioiox.dei.duc.db.service.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.db.mapper.slave.SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysApiSlaveDbSvc")
public class SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysApi, SysApiSlaveMapper>
        implements SysApiSlaveDbSvc {

    @Autowired
    private SysApiSlaveMapper mapper;

    @Override
    protected SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "系统API信息表";
    }
}
