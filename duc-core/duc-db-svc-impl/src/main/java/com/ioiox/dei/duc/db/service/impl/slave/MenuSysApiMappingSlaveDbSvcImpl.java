package com.ioiox.dei.duc.db.service.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.db.mapper.slave.MenuSysApiMappingSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.MenuSysApiMappingSlaveDbSvc;
import org.springframework.stereotype.Service;

@Service("menuSysApiMappingSlaveDbSvc")
public class MenuSysApiMappingSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<MenuSysApiMapping, MenuSysApiMappingSlaveMapper>
        implements MenuSysApiMappingSlaveDbSvc {

    private MenuSysApiMappingSlaveMapper mapper;

    @Override
    protected MenuSysApiMappingSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "菜单与系统API关系表";
    }
}
