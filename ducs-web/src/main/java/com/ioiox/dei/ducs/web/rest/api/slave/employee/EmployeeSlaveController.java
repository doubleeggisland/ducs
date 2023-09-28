package com.ioiox.dei.ducs.web.rest.api.slave.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeSlaveStdDataSvc;
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
public class EmployeeSlaveController {

    @Autowired
    @Qualifier("employeeSlaveStdDataSvc")
    private EmployeeSlaveStdDataSvc employeeSlaveStdDataSvc;

    @PostMapping(path = "/employees/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countEmployees(@RequestBody final EmployeeQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/employees/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<EmployeeSlaveVO>> queryEmployees(@RequestBody final EmployeeQueryReq queryReq) {
        return new DeiResponseData.Builder<List<EmployeeSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
