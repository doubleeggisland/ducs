package com.ioiox.dei.ducs.web.rest.api.slave.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeUserGrpQueryParam;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeUserGrpQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeUserGrpSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeUserGrpSlaveStdDataSvc;
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
public class EmployeeUserGrpSlaveController {

    @Autowired
    @Qualifier("employeeUserGrpSlaveStdDataSvc")
    private EmployeeUserGrpSlaveStdDataSvc employeeUserGrpSlaveStdDataSvc;

    @PostMapping(path = "/employee/user-grps/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countUserGrps(@RequestBody final EmployeeUserGrpQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeUserGrpSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/employee/user-grps/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<EmployeeUserGrpSlaveVO>> queryUserGrps(@RequestBody final EmployeeUserGrpQueryReq queryReq) {
        return new DeiResponseData.Builder<List<EmployeeUserGrpSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeUserGrpSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
