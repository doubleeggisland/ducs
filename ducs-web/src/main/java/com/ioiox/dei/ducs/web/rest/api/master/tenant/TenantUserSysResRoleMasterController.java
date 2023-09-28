package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserSysResRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantUserSysResRoleMasterController {

    @Autowired
    @Qualifier("tenantUserSysResRoleMasterStdDataSvc")
    private TenantUserSysResRoleMasterStdDataSvc tenantUserSysResRoleMasterStdDataSvc;

    @PostMapping(path = "/tenant-user/sys-res-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveSysResRole(@RequestBody final TenantUserSysResRoleMasterVO sysResRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserSysResRoleMasterStdDataSvc.save(sysResRole))
                .build();
    }

    @PutMapping(path = "/tenant-user/sys-res-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateSysResRole(@PathVariable("id") Long id,
                                                    @RequestBody final TenantUserSysResRoleMasterVO sysResRole) {
        sysResRole.setId(id);
        tenantUserSysResRoleMasterStdDataSvc.update(sysResRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("资源角色修改成功")
                .build();
    }

    @PostMapping(path = "/tenant-user/sys-res-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeSysResRoles(@RequestBody final TenantUserSysResRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserSysResRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
