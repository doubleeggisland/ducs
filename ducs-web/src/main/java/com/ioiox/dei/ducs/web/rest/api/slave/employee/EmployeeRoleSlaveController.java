package com.ioiox.dei.ducs.web.rest.api.slave.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/duc/common/api")
public class EmployeeRoleSlaveController {

    @Autowired
    @Qualifier("employeeRoleSlaveStdDataSvc")
    private EmployeeRoleSlaveStdDataSvc employeeRoleSlaveStdDataSvc;

    @PostMapping(path = "/employee/roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countRoles(@RequestBody final EmployeeRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/employee/roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<EmployeeRoleSlaveVO>> queryRoles(@RequestBody final EmployeeRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<EmployeeRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
