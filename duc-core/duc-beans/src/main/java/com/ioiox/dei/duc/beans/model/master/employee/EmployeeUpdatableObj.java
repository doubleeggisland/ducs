package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableDateAttr;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.beans.model.master.UserUpdatableObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUpdatableObj
        extends UserUpdatableObj {
    private UpdatableAttr<String> realName;
    private UpdatableDateAttr dateOfBirth;
    private UpdatableAttr<String> gender;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(BaseUser.ShowColumn.values().length + Employee.ShowColumn.values().length);
        updateSummary.putAll(super.updateSummary());
        if (Objects.nonNull(realName)) {
            updateSummary.put(realName.getAttrName(), String.valueOf(realName));
        }
        if (Objects.nonNull(dateOfBirth)) {
            updateSummary.put(dateOfBirth.getAttrName(), String.valueOf(dateOfBirth));
        }
        if (Objects.nonNull(gender)) {
            updateSummary.put(gender.getAttrName(), String.valueOf(gender));
        }
        return updateSummary;
    }
}
