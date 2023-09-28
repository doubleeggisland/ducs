package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserTmpSysResRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantUserTmpSysResRoleMasterController {

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleMasterStdDataSvc")
    private TenantUserTmpSysResRoleMasterStdDataSvc tenantUserTmpSysResRoleMasterStdDataSvc;

    @PostMapping(path = "/tenant-user/tmp-sys-res-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTmpSysResRole(@RequestBody final TenantUserTmpSysResRoleMasterVO tmpSysResRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpSysResRoleMasterStdDataSvc.save(tmpSysResRole))
                .build();
    }

    @PutMapping(path = "/tenant-user/tmp-sys-res-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTmpSysResRole(@PathVariable("id") Long id,
                                                       @RequestBody final TenantUserTmpSysResRoleMasterVO tmpSysResRole) {
        tmpSysResRole.setId(id);
        tenantUserTmpSysResRoleMasterStdDataSvc.update(tmpSysResRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("临时资源角色修改成功")
                .build();
    }

    @PostMapping(path = "/tenant-user/tmp-sys-res-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTmpSysResRoles(@RequestBody final TenantUserTmpSysResRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpSysResRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
