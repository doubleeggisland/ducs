package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantUserRoleMasterController {

    @Autowired
    @Qualifier("tenantUserRoleMasterStdDataSvc")
    private TenantUserRoleMasterStdDataSvc tenantUserRoleMasterStdDataSvc;

    @PostMapping(path = "/tenant-user/roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveRole(@RequestBody final TenantUserRoleMasterVO role) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserRoleMasterStdDataSvc.save(role))
                .build();
    }

    @PutMapping(path = "/tenant-user/roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateRole(@PathVariable("id") Long id,
                                              @RequestBody final TenantUserRoleMasterVO role) {
        role.setId(id);
        tenantUserRoleMasterStdDataSvc.update(role);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("角色修改成功")
                .build();
    }

    @PostMapping(path = "/tenant-user/roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeRoles(@RequestBody final TenantUserRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
