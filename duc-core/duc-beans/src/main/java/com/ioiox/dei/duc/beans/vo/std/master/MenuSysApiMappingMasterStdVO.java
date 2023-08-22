package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.spring.core.model.DUCSysApiInteractForm;
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
     * @see DUCSysApiInteractForm
     */
    private String interactForm;

    @Override
    public String uniqueKeyDigest() {
        final MenuSysApiMapping.UniqueKey uniqueKey = uniqueKey();
        return uniqueKey.toString();
    }

    public MenuSysApiMapping.UniqueKey uniqueKey() {
        return new MenuSysApiMapping.UniqueKey(menuId, sysApiId,
                StringUtils.isBlank(interactForm) ? DUCSysApiInteractForm.OTHER.getCode() : interactForm);
    }
}
