package com.ioiox.dei.ducs.web.rest.api.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserGrpMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserGrpMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class TenantUserGrpMasterController {

    @Autowired
    @Qualifier("tenantUserGrpMasterStdDataSvc")
    private TenantUserGrpMasterStdDataSvc tenantUserGrpMasterStdDataSvc;

    @PostMapping(path = "/tenant-user/user-grps/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveUserGrp(@RequestBody final TenantUserGrpMasterVO userGrp) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserGrpMasterStdDataSvc.save(userGrp))
                .build();
    }

    @PutMapping(path = "/tenant-user/user-grps/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateUserGrp(@PathVariable("id") Long id,
                                                 @RequestBody final TenantUserGrpMasterVO userGrp) {
        userGrp.setId(id);
        tenantUserGrpMasterStdDataSvc.update(userGrp);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户组修改成功")
                .build();
    }

    @PostMapping(path = "/tenant-user/user-grps/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeUserGrps(@RequestBody final TenantUserGrpDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(tenantUserGrpMasterStdDataSvc.remove(delParam))
                .build();
    }
}
