package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctTmpRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class UserAcctTmpRoleMasterController {

    @Autowired
    @Qualifier("userAcctTmpRoleMasterStdDataSvc")
    private UserAcctTmpRoleMasterStdDataSvc userAcctTmpRoleMasterStdDataSvc;

    @PostMapping(path = "/user-acct/tmp-roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveTmpRole(@RequestBody final UserAcctTmpRoleMasterVO tmpRole) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpRoleMasterStdDataSvc.save(tmpRole))
                .build();
    }

    @PutMapping(path = "/user-acct/tmp-roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateTmpRole(@PathVariable("id") Long id,
                                                 @RequestBody final UserAcctTmpRoleMasterVO tmpRole) {
        tmpRole.setId(id);
        userAcctTmpRoleMasterStdDataSvc.update(tmpRole);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户临时角色修改成功")
                .build();
    }

    @PostMapping(path = "/user-acct/tmp-roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeTmpRoles(@RequestBody final UserAcctRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctTmpRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
