package com.ioiox.dei.ducs.web.rest.api.slave.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpSysResRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctTmpSysResRoleSlaveStdDataSvc;
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
public class UserAcctTmpSysResRoleSlaveController {

    @Autowired
    @Qualifier("userAcctTmpSysResRoleSlaveStdDataSvc")
    private UserAcctTmpSysResRoleSlaveStdDataSvc userAcctTmpSysResRoleSlaveStdDataSvc;

    @PostMapping(path = "/user-acct/tmp-sys-res-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTmpSysResRoles(@RequestBody final UserAcctTmpSysResRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpSysResRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/user-acct/tmp-sys-res-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<UserAcctTmpSysResRoleSlaveVO>> queryTmpSysResRoles(@RequestBody final UserAcctTmpSysResRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<UserAcctTmpSysResRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpSysResRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
