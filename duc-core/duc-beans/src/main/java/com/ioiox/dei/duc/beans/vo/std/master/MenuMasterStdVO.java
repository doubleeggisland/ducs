package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuMasterStdVO extends MasterStdDataVO {
    private String code;
    private String name;
    private Long pid;
    private Integer lvl;
    private String routePath;
    private String componentUrl;
    private String redirectPath;
    private String isHidden;
    private String isCache;
    private String icon;
    private String status;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
    /**
     * 与菜单相关的系统API信息
     */
    private List<MenuSysApiMappingMasterVO> sysApiMappings;
}
