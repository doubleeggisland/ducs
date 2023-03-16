package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.SysRes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SysResUpdatableObj extends UpdatableVO {

    private UpdatableAttr<String> code;

    private UpdatableAttr<String> name;

    private UpdatableAttr<String> type;

    private UpdatableAttr<String> status;

    private UpdatableAttr<String> memo;

    private UpdatableAttr<String> sysPrjModuleName;

    private UpdatableAttr<String> sysPrjModuleCode;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(SysRes.ShowColumn.values().length);
        if (Objects.nonNull(code)) {
            updateSummary.put(code.getAttrName(), String.valueOf(code));
        }
        if (Objects.nonNull(name)) {
            updateSummary.put(name.getAttrName(), String.valueOf(name));
        }
        if (Objects.nonNull(type)) {
            updateSummary.put(type.getAttrName(), String.valueOf(type));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        if (Objects.nonNull(memo)) {
            updateSummary.put(memo.getAttrName(), String.valueOf(memo));
        }
        if (Objects.nonNull(sysPrjModuleName)) {
            updateSummary.put(sysPrjModuleName.getAttrName(), String.valueOf(sysPrjModuleName));
        }
        if (Objects.nonNull(sysPrjModuleCode)) {
            updateSummary.put(sysPrjModuleCode.getAttrName(), String.valueOf(sysPrjModuleCode));
        }
        return updateSummary;
    }
}
