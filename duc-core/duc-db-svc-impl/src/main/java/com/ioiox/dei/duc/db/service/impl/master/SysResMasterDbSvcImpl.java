package com.ioiox.dei.duc.db.service.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.db.mapper.master.SysResMasterMapper;
import com.ioiox.dei.duc.db.service.master.SysResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysResMasterDbSvc")
public class SysResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysRes, SysResMasterMapper>
        implements SysResMasterDbSvc {

    @Autowired
    private SysResMasterMapper mapper;

    @Override
    protected SysResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "系统资源信息表";
    }
}
