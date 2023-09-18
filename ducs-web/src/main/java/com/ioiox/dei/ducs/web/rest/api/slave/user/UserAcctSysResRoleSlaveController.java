package com.ioiox.dei.ducs.web.rest.api.slave.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctSysResRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSysResRoleSlaveStdDataSvc;
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
public class UserAcctSysResRoleSlaveController {

    @Autowired
    @Qualifier("userAcctSysResRoleSlaveStdDataSvc")
    private UserAcctSysResRoleSlaveStdDataSvc userAcctSysResRoleSlaveStdDataSvc;

    @PostMapping(path = "/user-acct/sys-res-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countSysResRoles(@RequestBody final UserAcctSysResRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctSysResRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/user-acct/sys-res-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<UserAcctSysResRoleSlaveVO>> querySysResRoles(@RequestBody final UserAcctSysResRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<UserAcctSysResRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctSysResRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
