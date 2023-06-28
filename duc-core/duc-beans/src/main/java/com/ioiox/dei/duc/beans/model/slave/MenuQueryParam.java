package com.ioiox.dei.duc.beans.model.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataQueryParam;
import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuQueryParam
        extends StdDataQueryParam {

    private List<Long> pids;
    private List<Long> sysPrjIds;
    private List<String> statuses;
    private Integer lvlFrom;
    private Integer lvlTo;

    private MenuQueryParam(final Builder builder) {
        super(builder);
        pids = builder.pids;
        sysPrjIds = builder.sysPrjIds;
        statuses = builder.statuses;
        lvlFrom = builder.lvlFrom;
        lvlTo = builder.lvlTo;
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (DeiCollectionUtil.isNotEmpty(this.pids)) {
            if (this.pids.size() > 1) {
                queryParams.put("pids", this.pids);
            } else {
                queryParams.put("pid", this.pids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.sysPrjIds)) {
            if (this.sysPrjIds.size() > 1) {
                queryParams.put("sysPrjSids", this.sysPrjIds);
            } else {
                queryParams.put("sysPrjSid", this.sysPrjIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.statuses)) {
            if (this.statuses.size() > 1) {
                queryParams.put("statuses", this.statuses);
            } else {
                queryParams.put("status", this.statuses.get(0));
            }
        }
        if (Objects.nonNull(lvlFrom)) {
            queryParams.put("lvlFrom", lvlFrom);
        }
        if (Objects.nonNull(lvlTo)) {
            queryParams.put("lvlTo", lvlTo);
        }
        return queryParams;
    }

    public static class Builder
            extends StdDataQueryParamBuilder<MenuQueryParam> {
        private List<Long> pids;
        private List<Long> sysPrjIds;
        private List<String> statuses;
        private Integer lvlFrom;
        private Integer lvlTo;

        public Builder pids(final List<Long> pids) {
            this.pids = pids;
            return this;
        }
        public Builder sysPrjIds(final List<Long> sysPrjIds) {
            this.sysPrjIds = sysPrjIds;
            return this;
        }
        public Builder statuses(final List<String> statuses) {
            this.statuses = statuses;
            return this;
        }
        public Builder lvlFrom(final Integer lvlFrom) {
            this.lvlFrom = lvlFrom;
            return this;
        }
        public Builder lvlTo(final Integer lvlTo) {
            this.lvlTo = lvlTo;
            return this;
        }

        @Override
        public MenuQueryParam build() {
            return new MenuQueryParam(this);
        }
    }
}
