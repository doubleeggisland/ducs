package com.ioiox.dei.ducs.web.rest.api.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeSysResRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeSysResRoleMasterController {

    @Autowired
    @Qualifier("employeeSysResRoleMasterStdDataSvc")
    private EmployeeSysResRoleMasterStdDataSvc employeeSysResRoleMasterStdDataSvc;

    @PostMapping(path = "/employee/sys-res-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveSysResRole(@RequestBody final EmployeeSysResRoleMasterVO sysResRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeSysResRoleMasterStdDataSvc.save(sysResRole))
                .build();
    }

    @PutMapping(path = "/employee/sys-res-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateSysResRole(@PathVariable("id") Long id,
                                                    @RequestBody final EmployeeSysResRoleMasterVO sysResRole) {
        sysResRole.setId(id);
        employeeSysResRoleMasterStdDataSvc.update(sysResRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("资源角色修改成功")
                .build();
    }

    @PostMapping(path = "/employee/sys-res-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeSysResRoles(@RequestBody final EmployeeSysResRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeSysResRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
