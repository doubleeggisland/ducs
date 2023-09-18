package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class UserAcctRoleMasterController {

    @Autowired
    @Qualifier("userAcctRoleMasterStdDataSvc")
    private UserAcctRoleMasterStdDataSvc userAcctRoleMasterStdDataSvc;

    @PostMapping(path = "/user-acct/roles/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveRole(@RequestBody final UserAcctRoleMasterVO role) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctRoleMasterStdDataSvc.save(role))
                .build();
    }

    @PutMapping(path = "/user-acct/roles/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateRole(@PathVariable("id") Long id,
                                                 @RequestBody final UserAcctRoleMasterVO role) {
        role.setId(id);
        userAcctRoleMasterStdDataSvc.update(role);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户角色修改成功")
                .build();
    }

    @PostMapping(path = "/user-acct/roles/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeRoles(@RequestBody final UserAcctRoleDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctRoleMasterStdDataSvc.remove(delParam))
                .build();
    }
}
