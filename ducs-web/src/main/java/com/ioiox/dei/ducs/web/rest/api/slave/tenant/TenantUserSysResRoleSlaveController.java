package com.ioiox.dei.ducs.web.rest.api.slave.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserSysResRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserSysResRoleSlaveStdDataSvc;
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
public class TenantUserSysResRoleSlaveController {

    @Autowired
    @Qualifier("tenantUserSysResRoleSlaveStdDataSvc")
    private TenantUserSysResRoleSlaveStdDataSvc tenantUserSysResRoleSlaveStdDataSvc;

    @PostMapping(path = "/tenant-user/sys-res-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countSysResRoles(@RequestBody final TenantUserSysResRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserSysResRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/tenant-user/sys-res-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<TenantUserSysResRoleSlaveVO>> querySysResRoles(@RequestBody final TenantUserSysResRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<TenantUserSysResRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserSysResRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
