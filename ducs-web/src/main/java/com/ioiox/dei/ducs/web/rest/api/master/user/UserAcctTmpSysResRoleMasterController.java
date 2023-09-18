package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctTmpSysResRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class UserAcctTmpSysResRoleMasterController {

    @Autowired
    @Qualifier("userAcctTmpSysResRoleMasterStdDataSvc")
    private UserAcctTmpSysResRoleMasterStdDataSvc userAcctTmpSysResRoleMasterStdDataSvc;

    @PostMapping(path = "/user-acct/tmp-sys-res-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTmpSysResRole(@RequestBody final UserAcctTmpSysResRoleMasterVO tmpSysResRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpSysResRoleMasterStdDataSvc.save(tmpSysResRole))
                .build();
    }

    @PutMapping(path = "/user-acct/tmp-sys-res-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTmpSysResRole(@PathVariable("id") Long id,
                                                       @RequestBody final UserAcctTmpSysResRoleMasterVO tmpSysResRole) {
        tmpSysResRole.setId(id);
        userAcctTmpSysResRoleMasterStdDataSvc.update(tmpSysResRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户临时资源角色修改成功")
                .build();
    }

    @PostMapping(path = "/user-acct/tmp-sys-res-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTmpSysResRoles(@RequestBody final UserAcctSysResRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpSysResRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
