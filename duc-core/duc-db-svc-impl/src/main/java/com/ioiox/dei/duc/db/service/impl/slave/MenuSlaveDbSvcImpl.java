package com.ioiox.dei.duc.db.service.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.db.mapper.slave.MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuSlaveDbSvc")
public class MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<Menu, MenuSlaveMapper>
        implements MenuSlaveDbSvc {

    @Autowired
    private MenuSlaveMapper mapper;

    @Override
    protected MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "菜单表";
    }
}
