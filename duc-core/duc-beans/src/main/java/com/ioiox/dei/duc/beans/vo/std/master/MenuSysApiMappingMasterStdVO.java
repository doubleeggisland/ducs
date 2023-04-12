package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

    @Override
    public String uniqueKeyDigest() {
        final MenuSysApiMapping.UniqueKey uniqueKey = uniqueKey();
        return uniqueKey.toString();
    }

    public MenuSysApiMapping.UniqueKey uniqueKey() {
        return new MenuSysApiMapping.UniqueKey(menuId, sysApiId,
                StringUtils.isBlank(interactForm) ? MenuSysApiMapping.InteractForm.OTHER.getCode() : interactForm);
    }
}
