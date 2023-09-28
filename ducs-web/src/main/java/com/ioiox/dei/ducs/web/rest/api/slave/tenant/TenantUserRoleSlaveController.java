package com.ioiox.dei.ducs.web.rest.api.slave.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserRoleSlaveStdDataSvc;
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
public class TenantUserRoleSlaveController {

    @Autowired
    @Qualifier("tenantUserRoleSlaveStdDataSvc")
    private TenantUserRoleSlaveStdDataSvc tenantUserRoleSlaveStdDataSvc;

    @PostMapping(path = "/tenant-user/roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countRoles(@RequestBody final TenantUserRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/tenant-user/roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<TenantUserRoleSlaveVO>> queryRoles(@RequestBody final TenantUserRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<TenantUserRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
