package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMappingSlaveStdVO extends SlaveStdDataVO {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 系统接口ID
     */
    private Long sysApiId;
    /**
     * 交互的形式
     * @see com.ioiox.dei.duc.beans.entity.MenuSysApiMapping.InteractForm
     */
    private String interactForm;

    /**
     * 关联的菜单
     */
    @JsonIgnore
    private MenuSlaveStdVO menu;

    /**
     * 关联的系统接口
     */
    private SysApiSlaveStdVO sysApi;
}
