package com.ioiox.dei.ducs.web.rest.api.slave;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.SysApiQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SysApiQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysApiSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/duc/common/api")
public class SysApiSlaveController {

    @Autowired
    @Qualifier("sysApiSlaveStdDataSvc")
    private SysApiSlaveStdDataSvc sysApiSlaveStdDataSvc;

    @PostMapping(path = "/sys-apis/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countSysApis(@RequestBody final SysApiQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysApiSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/sys-apis/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<SysApiSlaveVO>> querySysApis(@RequestBody final SysApiQueryReq queryReq) {
        return new DeiResponseData.Builder<List<SysApiSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysApiSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
