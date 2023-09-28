package com.ioiox.dei.ducs.web.rest.api.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeRoleMasterController {

    @Autowired
    @Qualifier("employeeRoleMasterStdDataSvc")
    private EmployeeRoleMasterStdDataSvc employeeRoleMasterStdDataSvc;

    @PostMapping(path = "/employee/roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveRole(@RequestBody final EmployeeRoleMasterVO role) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeRoleMasterStdDataSvc.save(role))
                .build();
    }

    @PutMapping(path = "/employee/roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateRole(@PathVariable("id") Long id,
                                              @RequestBody final EmployeeRoleMasterVO role) {
        role.setId(id);
        employeeRoleMasterStdDataSvc.update(role);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("角色修改成功")
                .build();
    }

    @PostMapping(path = "/employee/roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeRoles(@RequestBody final EmployeeRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
