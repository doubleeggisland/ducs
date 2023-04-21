package com.ioiox.dei.duc.beans.vo.std.master.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.vo.std.master.UserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserGrpDelParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class AcctUserGrpDelParam
        extends UserGrpDelParam {
    private List<Long> corpIds;

    private AcctUserGrpDelParam(final Builder builder) {
        super(builder);
        corpIds = builder.corpIds;
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
        if (DeiCollectionUtil.isNotEmpty(corpIds)) {
            if (corpIds.size() > 1) {
                deleteParams.put("corpSids", corpIds);
            } else {
                deleteParams.put("corpSid", corpIds.get(0));
            }
        }
        return deleteParams;
    }

    public static class Builder
            extends UserGrpDelParamBuilder<AcctUserGrpDelParam> {
        private List<Long> corpIds;

        public Builder corpIds(final List<Long> corpIds) {
            this.corpIds = corpIds;
            return this;
        }

        @Override
        public AcctUserGrpDelParam build() {
            return new AcctUserGrpDelParam(this);
        }
    }
}
