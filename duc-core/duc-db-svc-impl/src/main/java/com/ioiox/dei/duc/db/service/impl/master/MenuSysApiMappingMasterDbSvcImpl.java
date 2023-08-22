package com.ioiox.dei.duc.db.service.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.db.mapper.master.MenuSysApiMappingMasterMapper;
import com.ioiox.dei.duc.db.service.master.MenuSysApiMappingMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuSysApiMappingMasterDbSvc")
public class MenuSysApiMappingMasterDbSvcImpl
        extends BaseDeiMasterDbService<MenuSysApiMapping, MenuSysApiMappingMasterMapper>
        implements MenuSysApiMappingMasterDbSvc {

    @Autowired
    private MenuSysApiMappingMasterMapper mapper;

    @Override
    protected MenuSysApiMappingMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "菜单与系统API关系表";
    }
}
