package com.ioiox.dei.ducs.web.rest.api.slave.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpRoleQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserTmpRoleSlaveStdDataSvc;
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
public class TenantUserTmpRoleSlaveController {

    @Autowired
    @Qualifier("tenantUserTmpRoleSlaveStdDataSvc")
    private TenantUserTmpRoleSlaveStdDataSvc tenantUserTmpRoleSlaveStdDataSvc;

    @PostMapping(path = "/tenant-user/tmp-roles/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countTmpRoles(@RequestBody final TenantUserTmpRoleQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpRoleSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/tenant-user/tmp-roles/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<TenantUserTmpRoleSlaveVO>> queryTmpRoles(@RequestBody final TenantUserTmpRoleQueryReq queryReq) {
        return new DeiResponseData.Builder<List<TenantUserTmpRoleSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpRoleSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
