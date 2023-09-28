package com.ioiox.dei.ducs.web.rest.api.slave.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserGrpQueryParam;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserGrpQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserGrpSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserGrpSlaveStdDataSvc;
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
public class TenantUserGrpSlaveController {

    @Autowired
    @Qualifier("tenantUserGrpSlaveStdDataSvc")
    private TenantUserGrpSlaveStdDataSvc tenantUserGrpSlaveStdDataSvc;

    @PostMapping(path = "/tenant-user/user-grps/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countUserGrps(@RequestBody final TenantUserGrpQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserGrpSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/tenant-user/user-grps/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<TenantUserGrpSlaveVO>> queryUserGrps(@RequestBody final TenantUserGrpQueryReq queryReq) {
        return new DeiResponseData.Builder<List<TenantUserGrpSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserGrpSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
