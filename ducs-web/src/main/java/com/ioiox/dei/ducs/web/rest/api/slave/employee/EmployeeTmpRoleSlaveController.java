package com.ioiox.dei.ducs.web.rest.api.slave.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeTmpRoleSlaveStdDataSvc;
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
public class EmployeeTmpRoleSlaveController {

    @Autowired
    @Qualifier("employeeTmpRoleSlaveStdDataSvc")
    private EmployeeTmpRoleSlaveStdDataSvc employeeTmpRoleSlaveStdDataSvc;

    @PostMapping(path = "/employee/tmp-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTmpRoles(@RequestBody final EmployeeTmpRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/employee/tmp-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<EmployeeTmpRoleSlaveVO>> queryTmpRoles(@RequestBody final EmployeeTmpRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<EmployeeTmpRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
