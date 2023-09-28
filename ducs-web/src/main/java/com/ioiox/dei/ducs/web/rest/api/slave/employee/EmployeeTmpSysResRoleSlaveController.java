package com.ioiox.dei.ducs.web.rest.api.slave.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpSysResRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeTmpSysResRoleSlaveStdDataSvc;
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
public class EmployeeTmpSysResRoleSlaveController {

    @Autowired
    @Qualifier("employeeTmpSysResRoleSlaveStdDataSvc")
    private EmployeeTmpSysResRoleSlaveStdDataSvc employeeTmpSysResRoleSlaveStdDataSvc;

    @PostMapping(path = "/employee/tmp-sys-res-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTmpSysResRoles(@RequestBody final EmployeeTmpSysResRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpSysResRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/employee/tmp-sys-res-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<EmployeeTmpSysResRoleSlaveVO>> queryTmpSysResRoles(@RequestBody final EmployeeTmpSysResRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<EmployeeTmpSysResRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(employeeTmpSysResRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
