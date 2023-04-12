package com.ioiox.dei.duc.beans.vo.std.slave.tenant;

import com.ioiox.dei.core.vo.StdDataQueryParam;
import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TenantQueryParam
        extends StdDataQueryParam {
    private List<String> codes;
    private List<Long> pids;
    private List<Long> rids;
    private List<String> statuses;
    private Integer lvlFrom;
    private Integer lvlTo;

    private TenantQueryParam(final Builder builder) {
        super(builder);
        codes = builder.codes;
        pids = builder.pids;
        rids = builder.rids;
        statuses = builder.statuses;
        lvlFrom = builder.lvlFrom;
        lvlTo = builder.lvlTo;
    }

    public static class Builder
            extends StdDataQueryParamBuilder<TenantQueryParam> {
        private List<String> codes;
        private List<Long> pids;
        private List<Long> rids;
        private List<String> statuses;
        private Integer lvlFrom;
        private Integer lvlTo;

        public Builder codes(final List<String> codes) {
            this.codes = codes;
            return this;
        }
        public Builder pids(final List<Long> pids) {
            this.pids = pids;
            return this;
        }
        public Builder rids(final List<Long> rids) {
            this.rids = rids;
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
        public TenantQueryParam build() {
            return new TenantQueryParam(this);
        }
    }
}
