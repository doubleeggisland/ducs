package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParam;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParamBuilder;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMappingDelParam
        extends StdDataDelParam {
    private List<Long> menuIds;
    private List<Long> sysApiIds;

    private MenuSysApiMappingDelParam(final Builder builder) {
        super(builder);
        menuIds = builder.menuIds;
        sysApiIds = builder.sysApiIds;
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
        if (DeiCollectionUtil.isNotEmpty(menuIds)) {
            if (menuIds.size() > 1) {
                deleteParams.put("menuSids", menuIds);
            } else {
                deleteParams.put("menuSid", menuIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(sysApiIds)) {
            if (sysApiIds.size() > 1) {
                deleteParams.put("sysApiSids", sysApiIds);
            } else {
                deleteParams.put("sysApiSid", sysApiIds.get(0));
            }
        }
        return deleteParams;
    }

    public static class Builder
            extends StdDataDelParamBuilder<MenuSysApiMappingDelParam> {
        private List<Long> menuIds;
        private List<Long> sysApiIds;

        public Builder menuIds(final List<Long> menuIds) {
            this.menuIds = menuIds;
            return this;
        }
        public Builder sysApiIds(final List<Long> sysApiIds) {
            this.sysApiIds = sysApiIds;
            return this;
        }

        @Override
        public MenuSysApiMappingDelParam build() {
            return new MenuSysApiMappingDelParam(this);
        }
    }
}
