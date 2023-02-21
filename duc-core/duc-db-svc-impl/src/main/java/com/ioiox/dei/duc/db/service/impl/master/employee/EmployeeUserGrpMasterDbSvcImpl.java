package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeUserGrp;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeUserGrpMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeUserGrpMasterDbSvc")
public class EmployeeUserGrpMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeUserGrp, EmployeeUserGrpMasterMapper>
        implements EmployeeUserGrpMasterDbSvc {

    @Autowired
    private EmployeeUserGrpMasterMapper mapper;

    @Override
    protected EmployeeUserGrpMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组表";
    }
}
