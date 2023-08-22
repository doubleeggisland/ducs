package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.duc.beans.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class MenuUpdatableObj extends UpdatableObj {

    private UpdatableAttr<String> code;

    private UpdatableAttr<String> name;

    private UpdatableAttr<Long> pid;

    private UpdatableAttr<Integer> lvl;

    private UpdatableAttr<String> routePath;

    private UpdatableAttr<String> componentUrl;

    private UpdatableAttr<String> redirectPath;

    private UpdatableAttr<String> isHidden;

    private UpdatableAttr<String> isCache;

    private UpdatableAttr<String> icon;

    private UpdatableAttr<String> status;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(Menu.ShowColumn.values().length);
        if (Objects.nonNull(code)) {
            updateSummary.put(code.getAttrName(), String.valueOf(code));
        }
        if (Objects.nonNull(name)) {
            updateSummary.put(name.getAttrName(), String.valueOf(name));
        }
        if (Objects.nonNull(pid)) {
            updateSummary.put(pid.getAttrName(), String.valueOf(pid));
        }
        if (Objects.nonNull(lvl)) {
            updateSummary.put(lvl.getAttrName(), String.valueOf(lvl));
        }
        if (Objects.nonNull(routePath)) {
            updateSummary.put(routePath.getAttrName(), String.valueOf(routePath));
        }
        if (Objects.nonNull(componentUrl)) {
            updateSummary.put(componentUrl.getAttrName(), String.valueOf(componentUrl));
        }
        if (Objects.nonNull(redirectPath)) {
            updateSummary.put(redirectPath.getAttrName(), String.valueOf(redirectPath));
        }
        if (Objects.nonNull(isHidden)) {
            updateSummary.put(isHidden.getAttrName(), String.valueOf(isHidden));
        }
        if (Objects.nonNull(isCache)) {
            updateSummary.put(isCache.getAttrName(), String.valueOf(isCache));
        }
        if (Objects.nonNull(icon)) {
            updateSummary.put(icon.getAttrName(), String.valueOf(icon));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        return updateSummary;
    }
}