package com.ioiox.dei.ducs.web.rest.api.slave.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserQueryParam;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserSlaveStdDataSvc;
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
public class TenantUserSlaveController {

    @Autowired
    @Qualifier("tenantUserSlaveStdDataSvc")
    private TenantUserSlaveStdDataSvc tenantUserSlaveStdDataSvc;

    @PostMapping(path = "/tenant-users/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTenantUsers(@RequestBody final TenantUserQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/tenant-users/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<TenantUserSlaveVO>> queryTenantUsers(@RequestBody final TenantUserQueryReq queryReq) {
        return new DeiResponseData.Builder<List<TenantUserSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
