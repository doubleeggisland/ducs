package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeUserGrpR2UserMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpR2UserMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeUserGrpR2UserMasterDbSvc")
public class EmployeeUserGrpR2UserMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2User, EmployeeUserGrpR2UserMasterMapper>
        implements EmployeeUserGrpR2UserMasterDbSvc {

    @Autowired
    private EmployeeUserGrpR2UserMasterMapper mapper;

    @Override
    protected EmployeeUserGrpR2UserMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与雇员关联表";
    }
}
