package com.ioiox.dei.ducs.web.rest.api.slave.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctRoleSlaveStdDataSvc;
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
public class UserAcctRoleSlaveController {

    @Autowired
    @Qualifier("userAcctRoleSlaveStdDataSvc")
    private UserAcctRoleSlaveStdDataSvc userAcctRoleSlaveStdDataSvc;

    @PostMapping(path = "/user-acct/roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countRoles(@RequestBody final UserAcctRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/user-acct/roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<UserAcctRoleSlaveStdVO>> queryRoles(@RequestBody final UserAcctRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<UserAcctRoleSlaveStdVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
