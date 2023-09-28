package com.ioiox.dei.ducs.web.rest.api.slave.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpSysResRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserTmpSysResRoleSlaveStdDataSvc;
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
public class TenantUserTmpSysResRoleSlaveController {

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleSlaveStdDataSvc")
    private TenantUserTmpSysResRoleSlaveStdDataSvc tenantUserTmpSysResRoleSlaveStdDataSvc;

    @PostMapping(path = "/tenant-user/tmp-sys-res-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTmpSysResRoles(@RequestBody final TenantUserTmpSysResRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpSysResRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/tenant-user/tmp-sys-res-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<TenantUserTmpSysResRoleSlaveVO>> queryTmpSysResRoles(@RequestBody final TenantUserTmpSysResRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<TenantUserTmpSysResRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpSysResRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
