package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.user.AcctUserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.AcctUserGrpMasterStdVO;
import com.ioiox.dei.duc.std.data.svc.master.user.AcctUserGrpMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class AcctUserGrpMasterController {

    @Autowired
    @Qualifier("acctUserGrpMasterStdDataSvc")
    private AcctUserGrpMasterStdDataSvc acctUserGrpMasterStdDataSvc;

    @PostMapping(path = "/user-acct/user-grps/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveUserGrp(@RequestBody final AcctUserGrpMasterStdVO userGrp) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(acctUserGrpMasterStdDataSvc.save(userGrp))
                .build();
    }

    @PutMapping(path = "/user-acct/user-grps/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateUserGrp(@PathVariable("id") Long id,
                                                 @RequestBody final AcctUserGrpMasterStdVO userGrp) {
        userGrp.setId(id);
        acctUserGrpMasterStdDataSvc.update(userGrp);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("用户组修改成功")
                .build();
    }

    @PostMapping(path = "/user-acct/user-grps/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeUserGrps(@RequestBody final AcctUserGrpDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(acctUserGrpMasterStdDataSvc.remove(delParam))
                .build();
    }
}
