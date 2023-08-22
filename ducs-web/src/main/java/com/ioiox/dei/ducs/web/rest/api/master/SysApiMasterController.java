package com.ioiox.dei.ducs.web.rest.api.master;

import com.ioiox.dei.duc.std.data.svc.master.SysApiMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duc/common/api")
public class SysApiMasterController {

    @Autowired
    @Qualifier("sysApiMasterStdDataSvc")
    private SysApiMasterStdDataSvc sysApiMasterStdDataSvc;
}
