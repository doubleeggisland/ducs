package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctSysResRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class UserAcctSysResRoleMasterController {

    @Autowired
    @Qualifier("userAcctSysResRoleMasterStdDataSvc")
    private UserAcctSysResRoleMasterStdDataSvc userAcctSysResRoleMasterStdDataSvc;

    @PostMapping(path = "/user-acct/sys-res-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveSysResRole(@RequestBody final UserAcctSysResRoleMasterVO sysResRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctSysResRoleMasterStdDataSvc.save(sysResRole))
                .build();
    }

    @PutMapping(path = "/user-acct/sys-res-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateSysResRole(@PathVariable("id") Long id,
                                                    @RequestBody final UserAcctSysResRoleMasterVO sysResRole) {
        sysResRole.setId(id);
        userAcctSysResRoleMasterStdDataSvc.update(sysResRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户资源角色修改成功")
                .build();
    }

    @PostMapping(path = "/user-acct/sys-res-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeSysResRoles(@RequestBody final UserAcctSysResRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctSysResRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
