package com.ioiox.dei.duc.beans.model.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryParam;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class UserQueryParam extends StdDataQueryParam {
    private List<String> usernames;
    private List<String> mobiles;
    private List<String> emails;
    private List<String> statuses;
    private String nickNameLike;

    public UserQueryParam(final UserQueryParamBuilder<? extends UserQueryParam> builder) {
        super(builder);
        usernames = builder.usernames();
        mobiles = builder.mobiles();
        emails = builder.emails();
        statuses = builder.statuses();
        nickNameLike = builder.nickNameLike();
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (DeiCollectionUtil.isNotEmpty(usernames)) {
            if (usernames.size() > 1) {
                queryParams.put("usernames", usernames);
            } else {
                queryParams.put("username", usernames.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(mobiles)) {
            if (mobiles.size() > 1) {
                queryParams.put("mobiles", mobiles);
            } else {
                queryParams.put("mobile", mobiles.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(emails)) {
            if (emails.size() > 1) {
                queryParams.put("emails", emails);
            } else {
                queryParams.put("email", emails.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(statuses)) {
            if (statuses.size() > 1) {
                queryParams.put("statuses", statuses);
            } else {
                queryParams.put("status", statuses.get(0));
            }
        }
        if (StringUtils.isNotBlank(nickNameLike)) {
            queryParams.put("nickNameLike", nickNameLike);
        }
        return queryParams;
    }
}
