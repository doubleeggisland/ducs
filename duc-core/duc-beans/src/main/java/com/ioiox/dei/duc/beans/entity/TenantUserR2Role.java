package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserR2Role extends UserR2Role {

    public static List<TenantUserR2Role> instances(final List<Long> roleSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<TenantUserR2Role> entities = new ArrayList<>(roleSids.size());
        for (final Long roleSid : roleSids) {
            final TenantUserR2Role entity = new TenantUserR2Role();
            entity.setUserSid(tenantUserSid);
            entity.setRoleSid(roleSid);
            entity.setCreatedBy(operator);
            entity.setCreatedTime(createdTime);
            entity.setVersionNum(BaseDeiEntity.INIT_VERSION_NUM);
            entities.add(entity);
        }
        return entities;
    }
}
