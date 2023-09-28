package com.ioiox.dei.ducs.web.rest.api.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeTmpRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeTmpRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeTmpRoleMasterController {

    @Autowired
    @Qualifier("employeeTmpRoleMasterStdDataSvc")
    private EmployeeTmpRoleMasterStdDataSvc employeeTmpRoleMasterStdDataSvc;

    @PostMapping(path = "/employee/tmp-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTmpRole(@RequestBody final EmployeeTmpRoleMasterVO tmpRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpRoleMasterStdDataSvc.save(tmpRole))
                .build();
    }

    @PutMapping(path = "/employee/tmp-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTmpRole(@PathVariable("id") Long id,
                                                 @RequestBody final EmployeeTmpRoleMasterVO tmpRole) {
        tmpRole.setId(id);
        employeeTmpRoleMasterStdDataSvc.update(tmpRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("临时角色修改成功")
                .build();
    }

    @PostMapping(path = "/employee/tmp-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTmpRoles(@RequestBody final EmployeeTmpRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
