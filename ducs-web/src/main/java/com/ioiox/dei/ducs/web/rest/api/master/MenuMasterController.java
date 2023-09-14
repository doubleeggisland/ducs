package com.ioiox.dei.ducs.web.rest.api.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import com.ioiox.dei.duc.beans.model.master.MenuDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterStdVO;
import com.ioiox.dei.duc.std.data.svc.master.MenuMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duc/common/api")
public class MenuMasterController {

    @Autowired
    @Qualifier("menuMasterStdDataSvc")
    private MenuMasterStdDataSvc menuMasterStdDataSvc;

    @PostMapping(path = "/menus/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Long> saveMenu(@RequestBody final MenuMasterStdVO menu) {
        return new DeiResponseData.Builder<Long>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(menuMasterStdDataSvc.save(menu))
                .build();
    }

    @PutMapping(path = "/menus/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> updateMenu(@PathVariable("id") Long id,
                                              @RequestBody final MenuMasterStdVO menu) {
        menu.setId(id);
        menuMasterStdDataSvc.update(menu);
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .msg("菜单修改成功")
                .build();
    }

    @PostMapping(path = "/menus/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<Integer> removeMenus(@RequestBody final MenuDelParam delParam) {
        return new DeiResponseData.Builder<Integer>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data(menuMasterStdDataSvc.remove(delParam))
                .build();
    }
}
