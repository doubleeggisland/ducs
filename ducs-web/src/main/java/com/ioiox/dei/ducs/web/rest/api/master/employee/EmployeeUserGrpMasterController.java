package com.ioiox.dei.ducs.web.rest.api.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeUserGrpMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeUserGrpMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeUserGrpMasterController {

    @Autowired
    @Qualifier("employeeUserGrpMasterStdDataSvc")
    private EmployeeUserGrpMasterStdDataSvc employeeUserGrpMasterStdDataSvc;

    @PostMapping(path = "/employee/user-grps/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveUserGrp(@RequestBody final EmployeeUserGrpMasterVO userGrp) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeUserGrpMasterStdDataSvc.save(userGrp))
                .build();
    }

    @PutMapping(path = "/employee/user-grps/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateUserGrp(@PathVariable("id") Long id,
                                                 @RequestBody final EmployeeUserGrpMasterVO userGrp) {
        userGrp.setId(id);
        employeeUserGrpMasterStdDataSvc.update(userGrp);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户组修改成功")
                .build();
    }

    @PostMapping(path = "/employee/user-grps/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeUserGrps(@RequestBody final EmployeeUserGrpDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeUserGrpMasterStdDataSvc.remove(delParam))
                .build();
    }
}
