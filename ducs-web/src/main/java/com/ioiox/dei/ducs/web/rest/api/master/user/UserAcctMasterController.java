package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class UserAcctMasterController {

    @Autowired
    @Qualifier("userAcctMasterStdDataSvc")
    private UserAcctMasterStdDataSvc userAcctMasterStdDataSvc;

    @PostMapping(path = "/user-accts/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveUserGrp(@RequestBody final UserAcctMasterVO userAcct) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctMasterStdDataSvc.save(userAcct))
                .build();
    }

    @PutMapping(path = "/user-accts/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateUserGrp(@PathVariable("id") Long id,
                                                 @RequestBody final UserAcctMasterVO userAcct) {
        userAcct.setId(id);
        userAcctMasterStdDataSvc.update(userAcct);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户账号修改成功")
                .build();
    }

    @PostMapping(path = "/user-accts/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeUserGrps(@RequestBody final UserAcctDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(userAcctMasterStdDataSvc.remove(delParam))
                .build();
    }
}
