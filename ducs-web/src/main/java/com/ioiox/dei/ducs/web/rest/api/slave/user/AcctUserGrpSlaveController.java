package com.ioiox.dei.ducs.web.rest.api.slave.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.user.AcctUserGrpQueryParam;
import com.ioiox.dei.duc.beans.model.slave.user.AcctUserGrpQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.user.AcctUserGrpSlaveStdDataSvc;
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
public class AcctUserGrpSlaveController {

    @Autowired
    @Qualifier("acctUserGrpSlaveStdDataSvc")
    private AcctUserGrpSlaveStdDataSvc acctUserGrpSlaveStdDataSvc;

    @PostMapping(path = "/acct-user-grps/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countUserGrps(@RequestBody final AcctUserGrpQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(acctUserGrpSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/acct-user-grps/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<AcctUserGrpSlaveStdVO>> queryUserGrps(@RequestBody final AcctUserGrpQueryReq queryReq) {
        return new DeiResponseData.Builder<List<AcctUserGrpSlaveStdVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(acctUserGrpSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
