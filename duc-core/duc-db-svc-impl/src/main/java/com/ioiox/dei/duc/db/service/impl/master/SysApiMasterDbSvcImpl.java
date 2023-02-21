package com.ioiox.dei.duc.db.service.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.db.mapper.master.SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysApiMasterDbSvc")
public class SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysApi, SysApiMasterMapper>
        implements SysApiMasterDbSvc {

    @Autowired
    private SysApiMasterMapper mapper;

    @Override
    protected SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "系统API信息表";
    }
}
