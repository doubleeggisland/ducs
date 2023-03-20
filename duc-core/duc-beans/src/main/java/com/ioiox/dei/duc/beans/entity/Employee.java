package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Employee extends BaseUser {
    /**
     * 姓名
     */
    private String realName;
    /**
     * 出生日期
     */
    private Date dateOfBirth;
    /**
     * 性别
     */
    private String gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        if (Objects.nonNull(getSid())
                && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else if (StringUtils.isNotBlank(getUsername())
                && StringUtils.isNotBlank(that.getUsername())) {
            return StringUtils.equals(getUsername(), that.getUsername());
        } else if (StringUtils.isNotBlank(getMobile())
                && StringUtils.isNotBlank(that.getMobile())) {
            return StringUtils.equals(getMobile(), that.getMobile());
        } else {
            return StringUtils.equals(getEmail(), that.getEmail());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getMobile(), getEmail());
    }

    public void setDefaultValueIfNeed() {
        super.setDefaultValueIfNeed();
        if (StringUtils.isBlank(realName)) {
            realName = DeiGlobalConstant.UNKNOWN_CN;
        }
        if (StringUtils.isBlank(gender)) {
            gender = Gender.UNKNOWN.getCode();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        REAL_NAME("realName", "姓名"),
        DATE_OF_BIRTH("dateOfBirth", "出生日期"),
        GENDER("gender", "性别"),
        ;
        private final String code;
        private final String desc;
    }
}
