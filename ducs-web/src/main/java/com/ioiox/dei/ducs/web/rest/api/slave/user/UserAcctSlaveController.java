package com.ioiox.dei.ducs.web.rest.api.slave.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctQueryParam;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSlaveStdDataSvc;
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
public class UserAcctSlaveController {

    @Autowired
    @Qualifier("userAcctSlaveStdDataSvc")
    private UserAcctSlaveStdDataSvc userAcctSlaveStdDataSvc;

    @PostMapping(path = "/user-accts/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countUserAccts(@RequestBody final UserAcctQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/user-accts/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<UserAcctSlaveVO>> queryUserAccts(@RequestBody final UserAcctQueryReq queryReq) {
        return new DeiResponseData.Builder<List<UserAcctSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
