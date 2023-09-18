package com.ioiox.dei.ducs.web.rest.api.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.SysResDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.SysResMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class SysResMasterController {

    @Autowired
    @Qualifier("sysResMasterStdDataSvc")
    private SysResMasterStdDataSvc sysResMasterStdDataSvc;

    @PostMapping(path = "/sys-resources/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveSysApi(@RequestBody final SysResMasterVO sysRes) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysResMasterStdDataSvc.save(sysRes))
                .build();
    }

    @PutMapping(path = "/sys-resources/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateSysApi(@PathVariable("id") Long id,
                                                @RequestBody final SysResMasterVO sysRes) {
        sysRes.setId(id);
        sysResMasterStdDataSvc.update(sysRes);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("系统服务信息修改成功")
                .build();
    }

    @PostMapping(path = "/sys-resources/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeSysApis(@RequestBody final SysResDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysResMasterStdDataSvc.remove(delParam))
                .build();
    }
}
