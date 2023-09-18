package com.ioiox.dei.ducs.web.rest.api.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.SysApiDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterVO;
import com.ioiox.dei.duc.std.data.svc.master.SysApiMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class SysApiMasterController {

    @Autowired
    @Qualifier("sysApiMasterStdDataSvc")
    private SysApiMasterStdDataSvc sysApiMasterStdDataSvc;

    @PostMapping(path = "/sys-apis/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveSysApi(@RequestBody final SysApiMasterVO sysApi) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysApiMasterStdDataSvc.save(sysApi))
                .build();
    }

    @PutMapping(path = "/sys-apis/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateSysApi(@PathVariable("id") Long id,
                                                @RequestBody final SysApiMasterVO sysApi) {
        sysApi.setId(id);
        sysApiMasterStdDataSvc.update(sysApi);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("系统接口信息修改成功")
                .build();
    }

    @PostMapping(path = "/sys-apis/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeSysApis(@RequestBody final SysApiDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(sysApiMasterStdDataSvc.remove(delParam))
                .build();
    }
}
