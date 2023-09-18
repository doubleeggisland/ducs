package com.ioiox.dei.ducs.web.rest.api.slave;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryParam;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryReq;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSlaveStdDataSvc;
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
public class MenuSlaveController {

    @Autowired
    @Qualifier("menuSlaveStdDataSvc")
    private MenuSlaveStdDataSvc menuSlaveStdDataSvc;

    @PostMapping(path = "/menus/count", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> countMenus(@RequestBody final MenuQueryParam queryParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(menuSlaveStdDataSvc.countByParam(queryParam))
                .build();
    }

    @PostMapping(path = "/menus/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<List<MenuSlaveVO>> queryMenus(@RequestBody final MenuQueryReq queryReq) {
        return new DeiResponseData.Builder<List<MenuSlaveVO>>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(menuSlaveStdDataSvc.queryByParam(queryReq.getQueryParam(), queryReq.getQueryCfg()))
                .build();
    }
}
