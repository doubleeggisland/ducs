package com.ioiox.dei.ducs.web.rest.api.master;

import com.ioiox.dei.duc.std.data.svc.master.SysPrjMasterStdDataSvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duc/common/api")
public class SysPrjMasterController {

    private SysPrjMasterStdDataSvc sysPrjMasterStdDataSvc;
}
