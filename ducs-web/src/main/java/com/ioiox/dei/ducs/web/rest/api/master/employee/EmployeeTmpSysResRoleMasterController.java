package com.ioiox.dei.ducs.web.rest.api.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeTmpSysResRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeTmpSysResRoleMasterController {

    @Autowired
    @Qualifier("employeeTmpSysResRoleMasterStdDataSvc")
    private EmployeeTmpSysResRoleMasterStdDataSvc employeeTmpSysResRoleMasterStdDataSvc;

    @PostMapping(path = "/employee/tmp-sys-res-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTmpSysResRole(@RequestBody final EmployeeTmpSysResRoleMasterVO tmpSysResRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpSysResRoleMasterStdDataSvc.save(tmpSysResRole))
                .build();
    }

    @PutMapping(path = "/employee/tmp-sys-res-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTmpSysResRole(@PathVariable("id") Long id,
                                                       @RequestBody final EmployeeTmpSysResRoleMasterVO tmpSysResRole) {
        tmpSysResRole.setId(id);
        employeeTmpSysResRoleMasterStdDataSvc.update(tmpSysResRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("临时资源角色修改成功")
                .build();
    }

    @PostMapping(path = "/employee/tmp-sys-res-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTmpSysResRoles(@RequestBody final EmployeeTmpSysResRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpSysResRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
