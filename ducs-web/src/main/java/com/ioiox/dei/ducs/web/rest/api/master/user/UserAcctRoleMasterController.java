package com.ioiox.dei.ducs.web.rest.api.master.user;

import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctRoleMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duc/common/api")
public class UserAcctRoleMasterController {

    @Autowired
    @Qualifier("userAcctRoleMasterStdDataSvc")
    private UserAcctRoleMasterStdDataSvc userAcctRoleMasterStdDataSvc;
}
