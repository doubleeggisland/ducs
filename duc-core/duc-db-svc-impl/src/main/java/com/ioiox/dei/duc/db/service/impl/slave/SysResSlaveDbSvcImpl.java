package com.ioiox.dei.duc.db.service.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.db.mapper.slave.SysResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.SysResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysResSlaveDbSvc")
public class SysResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysRes, SysResSlaveMapper>
        implements SysResSlaveDbSvc {

    @Autowired
    private SysResSlaveMapper mapper;

    @Override
    protected SysResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "系统资源信息表";
    }
}
