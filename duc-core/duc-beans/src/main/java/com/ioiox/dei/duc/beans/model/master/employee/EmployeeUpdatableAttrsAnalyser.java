package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableDateAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.beans.model.master.UserUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSlaveVO;

import java.util.Date;
import java.util.Objects;

public class EmployeeUpdatableAttrsAnalyser
        extends UserUpdatableAttrsAnalyser<EmployeeMasterVO, EmployeeSlaveVO, EmployeeUpdatableObj, EmployeeUpdateCtx> {

    @Override
    public EmployeeUpdateCtx analyseUpdatedAttrs(final EmployeeMasterVO employee,
                                                 final EmployeeSlaveVO existingEmployee) {
        final EmployeeUpdateCtx updateCtx = new EmployeeUpdateCtx();
        updateCtx.setUpdatableObj(new EmployeeUpdatableObj());
        analyseUpdatedAttrs(employee, existingEmployee, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final EmployeeMasterVO employee,
                                       final EmployeeSlaveVO existingEmployee,
                                       final EmployeeUpdateCtx updateCtx) {
        super.analyseUpdatedAttrs(employee, existingEmployee, updateCtx);
        if (UpdatableObj.modified(existingEmployee.getRealName(), employee.getRealName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Employee.ShowColumn.REAL_NAME.getCode());
            updateCtx.getUpdatableObj().setUsername(new UpdatableAttr<>(Employee.ShowColumn.REAL_NAME.getCode(), existingEmployee.getRealName(), employee.getRealName()));
        }
        if (UpdatableObj.modified(existingEmployee.getDateOfBirth(), employee.getDateOfBirth())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Employee.ShowColumn.DATE_OF_BIRTH.getCode());
            final Date oldDateOfBirth = Objects.isNull(existingEmployee.getDateOfBirth()) ? null : new Date(existingEmployee.getDateOfBirth());
            final Date newDateOfBirth = Objects.isNull(employee.getDateOfBirth()) ? null : new Date(employee.getDateOfBirth());
            updateCtx.getUpdatableObj().setDateOfBirth(new UpdatableDateAttr(Employee.ShowColumn.DATE_OF_BIRTH.getCode(), oldDateOfBirth, newDateOfBirth));
        }
        if (UpdatableObj.modified(existingEmployee.getGender(), employee.getGender())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Employee.ShowColumn.GENDER.getCode());
            updateCtx.getUpdatableObj().setGender(new UpdatableAttr<>(Employee.ShowColumn.GENDER.getCode(), existingEmployee.getGender(), employee.getGender()));
        }
    }
}
