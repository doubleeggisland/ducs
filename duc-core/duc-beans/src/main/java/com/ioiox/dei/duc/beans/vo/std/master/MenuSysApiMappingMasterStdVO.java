package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMappingMasterStdVO extends MasterStdDataVO {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 系统接口ID
     */
    private Long sysApiId;
    /**
     * 交互形式
     * @see com.ioiox.dei.duc.beans.entity.MenuSysApiMapping.InteractForm
     */
    private String interactForm;
}
