package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.core.orm.mybatis.model.std.data.SlaveStdDataVO;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
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
     * @see com.ioiox.dei.duc.spring.core.model.DUCSysApiInteractForm
     */
    private String interactForm;

    /**
     * 关联的菜单
     */
    @JsonIgnore
    private MenuSlaveVO menu;

    /**
     * 关联的系统接口
     */
    private SysApiSlaveVO sysApi;

    @Override
    public String uniqueKeyDigest() {
        final MenuSysApiMapping.UniqueKey uniqueKey = uniqueKey();
        return uniqueKey.toString();
    }

    public MenuSysApiMapping.UniqueKey uniqueKey() {
        return new MenuSysApiMapping.UniqueKey(menuId, sysApiId, interactForm);
    }
}
