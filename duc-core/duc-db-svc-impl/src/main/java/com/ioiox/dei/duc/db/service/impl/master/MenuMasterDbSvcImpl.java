package com.ioiox.dei.duc.db.service.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.db.mapper.master.MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuMasterDbSvc")
public class MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<Menu, MenuMasterMapper>
        implements MenuMasterDbSvc {

    @Autowired
    private MenuMasterMapper mapper;

    @Override
    protected MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "菜单表";
    }
}
