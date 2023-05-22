package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.duc.spring.core.model.DUCSysApiInteractForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMapping extends BaseDeiEntity {
    private Long menuSid;
    private Long sysApiSid;
    /**
     * 交互方式
     * @see DUCSysApiInteractForm
     */
    private String interactForm;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(interactForm)) {
            interactForm = DUCSysApiInteractForm.OTHER.getCode();
        }
    }

    public UniqueKey uniqueKey() {
        return new UniqueKey(menuSid, sysApiSid, interactForm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuSysApiMapping that = (MenuSysApiMapping) o;
        return Objects.equals(uniqueKey(), that.uniqueKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueKey());
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        MENU_SID("menuSid", "菜单主键ID"),
        SYS_API_SID("sysApiSid", "系统API的主键ID"),
        INTERACT_FORM("interactForm", "交互形式"),
        ;

        private final String code;
        private final String desc;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UniqueKey {
        private Long menuSid;
        private Long sysApiSid;
        private String interactForm;

        public UniqueKey(final Long menuSid, final Long sysApiSid, final String interactForm) {
            this.menuSid = menuSid;
            this.sysApiSid = sysApiSid;
            this.interactForm = interactForm;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqueKey uniqueKey = (UniqueKey) o;
            return Objects.equals(menuSid, uniqueKey.menuSid)
                    && Objects.equals(sysApiSid, uniqueKey.sysApiSid)
                    && Objects.equals(interactForm, uniqueKey.interactForm);
        }

        @Override
        public int hashCode() {
            return Objects.hash(menuSid, sysApiSid, interactForm);
        }

        @Override
        public String toString() {
            return String.format("%s-%s-%s",
                    menuSid, sysApiSid, interactForm);
        }
    }
}
