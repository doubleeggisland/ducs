package com.ioiox.dei.ducs.web.rest.api.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeMasterController {

    @Autowired
    @Qualifier("employeeMasterStdDataSvc")
    private EmployeeMasterStdDataSvc employeeMasterStdDataSvc;

    @PostMapping(path = "/employees/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveEmployee(@RequestBody final EmployeeMasterVO employee) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeMasterStdDataSvc.save(employee))
                .build();
    }

    @PutMapping(path = "/employees/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateEmployee(@PathVariable("id") Long id,
                                                  @RequestBody final EmployeeMasterVO employee) {
        employee.setId(id);
        employeeMasterStdDataSvc.update(employee);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("雇员修改成功")
                .build();
    }

    @PostMapping(path = "/employees/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeEmployees(@RequestBody final EmployeeDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeMasterStdDataSvc.remove(delParam))
                .build();
    }
}
