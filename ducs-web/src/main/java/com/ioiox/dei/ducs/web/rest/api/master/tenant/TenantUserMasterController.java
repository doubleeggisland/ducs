package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantUserMasterController {

    @Autowired
    @Qualifier("tenantUserMasterStdDataSvc")
    private TenantUserMasterStdDataSvc tenantUserMasterStdDataSvc;

    @PostMapping(path = "/tenant-users/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTenantUser(@RequestBody final TenantUserMasterVO tenantUser) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserMasterStdDataSvc.save(tenantUser))
                .build();
    }

    @PutMapping(path = "/tenant-users/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTenantUser(@PathVariable("id") Long id,
                                                    @RequestBody final TenantUserMasterVO tenantUser) {
        tenantUser.setId(id);
        tenantUserMasterStdDataSvc.update(tenantUser);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("租户用户修改成功")
                .build();
    }

    @PostMapping(path = "/tenant-users/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTenantUsers(@RequestBody final TenantUserDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserMasterStdDataSvc.remove(delParam))
                .build();
    }
}
