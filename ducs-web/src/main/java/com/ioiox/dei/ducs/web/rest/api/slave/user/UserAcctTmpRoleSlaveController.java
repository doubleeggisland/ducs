package com.ioiox.dei.ducs.web.rest.api.slave.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctTmpRoleSlaveStdDataSvc;
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
public class UserAcctTmpRoleSlaveController {

    @Autowired
    @Qualifier("userAcctTmpRoleSlaveStdDataSvc")
    private UserAcctTmpRoleSlaveStdDataSvc userAcctTmpRoleSlaveStdDataSvc;

    @PostMapping(path = "/user-acct-tmp-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTmpRoles(@RequestBody final UserAcctTmpRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/user-acct-tmp-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<UserAcctTmpRoleSlaveStdVO>> queryTmpRoles(@RequestBody final UserAcctTmpRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<UserAcctTmpRoleSlaveStdVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
