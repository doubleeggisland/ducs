package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantMasterController {

    @Autowired
    @Qualifier("tenantMasterStdDataSvc")
    private TenantMasterStdDataSvc tenantMasterStdDataSvc;

    @PostMapping(path = "/tenants/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTenant(@RequestBody final TenantMasterVO tenant) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantMasterStdDataSvc.save(tenant))
                .build();
    }

    @PutMapping(path = "/tenants/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTenant(@PathVariable("id") Long id,
                                                 @RequestBody final TenantMasterVO tenant) {
        tenant.setId(id);
        tenantMasterStdDataSvc.update(tenant);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("租户修改成功")
                .build();
    }

    @PostMapping(path = "/tenants/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTenants(@RequestBody final TenantDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantMasterStdDataSvc.remove(delParam))
                .build();
    }
}
