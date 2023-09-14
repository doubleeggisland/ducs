package com.ioiox.dei.ducs.web.rest.api.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.SysPrjDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterStdVO;
import com.ioiox.dei.duc.std.data.svc.master.SysPrjMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class SysPrjMasterController {

    @Autowired
    @Qualifier("sysPrjMasterStdDataSvc")
    private SysPrjMasterStdDataSvc sysPrjMasterStdDataSvc;

    @PostMapping(path = "/sys-prjs/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveSysApi(@RequestBody final SysPrjMasterStdVO sysPrj) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysPrjMasterStdDataSvc.save(sysPrj))
                .build();
    }

    @PutMapping(path = "/sys-prjs/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateSysApi(@PathVariable("id") Long id,
                                                @RequestBody final SysPrjMasterStdVO sysPrj) {
        sysPrj.setId(id);
        sysPrjMasterStdDataSvc.update(sysPrj);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("系统服务信息修改成功")
                .build();
    }

    @PostMapping(path = "/sys-prjs/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeSysApis(@RequestBody final SysPrjDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysPrjMasterStdDataSvc.remove(delParam))
                .build();
    }
}
