package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserTmpRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserTmpRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantUserTmpRoleMasterController {

    @Autowired
    @Qualifier("tenantUserTmpRoleMasterStdDataSvc")
    private TenantUserTmpRoleMasterStdDataSvc tenantUserTmpRoleMasterStdDataSvc;

    @PostMapping(path = "/tenant-user/tmp-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTmpRole(@RequestBody final TenantUserTmpRoleMasterVO tmpRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpRoleMasterStdDataSvc.save(tmpRole))
                .build();
    }

    @PutMapping(path = "/tenant-user/tmp-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTmpRole(@PathVariable("id") Long id,
                                                 @RequestBody final TenantUserTmpRoleMasterVO tmpRole) {
        tmpRole.setId(id);
        tenantUserTmpRoleMasterStdDataSvc.update(tmpRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("临时角色修改成功")
                .build();
    }

    @PostMapping(path = "/tenant-user/tmp-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTmpRoles(@RequestBody final TenantUserTmpRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserTmpRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
