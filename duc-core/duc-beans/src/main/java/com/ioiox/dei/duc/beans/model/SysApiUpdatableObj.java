package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.SysApi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SysApiUpdatableObj extends UpdatableVO {

    private UpdatableAttr<String> code;

    private UpdatableAttr<String> name;

    private UpdatableAttr<String> type;

    private UpdatableAttr<String> memo;

    private UpdatableAttr<String> url;

    private UpdatableAttr<String> httpMethod;

    private UpdatableAttr<String> sysPrjModuleName;

    private UpdatableAttr<String> sysPrjModuleCode;

    private UpdatableAttr<String> status;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(SysApi.ShowColumn.values().length);
        if (Objects.nonNull(code)) {
            updateSummary.put(code.getAttrName(), String.valueOf(code));
        }
        if (Objects.nonNull(name)) {
            updateSummary.put(name.getAttrName(), String.valueOf(name));
        }
        if (Objects.nonNull(type)) {
            updateSummary.put(type.getAttrName(), String.valueOf(type));
        }
        if (Objects.nonNull(memo)) {
            updateSummary.put(memo.getAttrName(), String.valueOf(memo));
        }
        if (Objects.nonNull(url)) {
            updateSummary.put(url.getAttrName(), String.valueOf(url));
        }
        if (Objects.nonNull(httpMethod)) {
            updateSummary.put(httpMethod.getAttrName(), String.valueOf(httpMethod));
        }
        if (Objects.nonNull(sysPrjModuleName)) {
            updateSummary.put(sysPrjModuleName.getAttrName(), String.valueOf(sysPrjModuleName));
        }
        if (Objects.nonNull(sysPrjModuleCode)) {
            updateSummary.put(sysPrjModuleCode.getAttrName(), String.valueOf(sysPrjModuleCode));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        return updateSummary;
    }
}