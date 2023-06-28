package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMappingUpdatableObj
        extends UpdatableVO {

    private UpdatableAttr<String> interactForm;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(MenuSysApiMapping.ShowColumn.values().length);
        if (Objects.nonNull(interactForm)) {
            updateSummary.put(interactForm.getAttrName(), String.valueOf(interactForm));
        }
        return updateSummary;
    }
}
