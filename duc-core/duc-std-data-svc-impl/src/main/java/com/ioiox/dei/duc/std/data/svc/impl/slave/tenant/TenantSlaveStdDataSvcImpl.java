package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.*;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.*;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("tenantSlaveStdDataSvc")
public class TenantSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<TenantSlaveVO, Tenant>
        implements TenantSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantSlaveDbSvc")
    private TenantSlaveDbSvc tenantSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserGrpSlaveStdDataSvc")
    private TenantUserGrpSlaveStdDataSvc tenantUserGrpSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserSlaveStdDataSvc")
    private TenantUserSlaveStdDataSvc tenantUserSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserRoleSlaveStdDataSvc")
    private TenantUserRoleSlaveStdDataSvc tenantUserRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserSysResRoleSlaveStdDataSvc")
    private TenantUserSysResRoleSlaveStdDataSvc tenantUserSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleSlaveStdDataSvc")
    private TenantUserTmpRoleSlaveStdDataSvc tenantUserTmpRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleSlaveStdDataSvc")
    private TenantUserTmpSysResRoleSlaveStdDataSvc tenantUserTmpSysResRoleSlaveStdDataSvc;

    @Override
    public TenantSlaveVO queryByPk(final Long pk,
                                   final TenantQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<TenantSlaveVO> tenants =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(tenants)) {
            return null;
        }
        return tenants.get(0);
    }

    @Override
    public List<TenantSlaveVO> queryByPks(final List<Long> pks,
                                          final TenantQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final TenantQueryParam queryParam = new TenantQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public int countByParam(final TenantQueryParam queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return tenantSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    public List<TenantSlaveVO> queryByParam(final TenantQueryParam queryParam,
                                            final TenantQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)) {
            if (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedUserGrps())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTenantUsers())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedRoles())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResRoles())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpRoles())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpSysResRoles())) {
                addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
            }
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<Tenant> entities = tenantSlaveDbSvc.findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }
        final List<TenantSlaveVO> tenants = new ArrayList<>(entities.size());
        final List<Long> tenantIds = new ArrayList<>(entities.size());
        for (final Tenant entity : entities) {
            tenantIds.add(entity.getSid());
            tenants.add(transferToStdDataVO(entity));
        }

        final Map<Long, List<TenantUserGrpSlaveVO>> userGrps;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedUserGrps())) {
            userGrps = getUserGrps(tenantIds, queryCfg.getUserGrpQueryCfg());
        } else {
            userGrps = Collections.emptyMap();
        }
        final Map<Long, List<TenantUserSlaveVO>> users;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTenantUsers())) {
            users = getUsers(tenantIds, queryCfg.getTenantUserQueryCfg());
        } else {
            users = Collections.emptyMap();
        }
        final Map<Long, List<TenantUserRoleSlaveVO>> roles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedRoles())) {
            roles = getRoles(tenantIds, queryCfg.getRoleQueryCfg());
        } else {
            roles = Collections.emptyMap();
        }
        final Map<Long, List<TenantUserSysResRoleSlaveVO>> sysResRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResRoles())) {
            sysResRoles = getSysResRoles(tenantIds, queryCfg.getSysResRoleQueryCfg());
        } else {
            sysResRoles = Collections.emptyMap();
        }
        final Map<Long, List<TenantUserTmpRoleSlaveVO>> tmpRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpRoles())) {
            tmpRoles = getTmpRoles(tenantIds, queryCfg.getTmpRoleQueryCfg());
        } else {
            tmpRoles = Collections.emptyMap();
        }
        final Map<Long, List<TenantUserTmpSysResRoleSlaveVO>> tmpSysResRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpSysResRoles())) {
            tmpSysResRoles = getTmpSysResRoles(tenantIds, queryCfg.getTmpSysResRoleQueryCfg());
        } else {
            tmpSysResRoles = Collections.emptyMap();
        }

        tenants.forEach(tenant -> {
            tenant.setUserGrps(userGrps.getOrDefault(tenant.getId(), Collections.emptyList()));
            tenant.setUsers(users.getOrDefault(tenant.getId(), Collections.emptyList()));
            tenant.setRoles(roles.getOrDefault(tenant.getId(), Collections.emptyList()));
            tenant.setSysResRoles(sysResRoles.getOrDefault(tenant.getId(), Collections.emptyList()));
            tenant.setTmpRoles(tmpRoles.getOrDefault(tenant.getId(), Collections.emptyList()));
            tenant.setTmpSysResRoles(tmpSysResRoles.getOrDefault(tenant.getId(), Collections.emptyList()));
        });
        return tenants;
    }

    Map<Long, List<TenantUserGrpSlaveVO>> getUserGrps(final List<Long> tenantIds, final UserGrpQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(TenantUserGrp.ShowColumn.TENANT_SID.getCode()));
        final List<TenantUserGrpSlaveVO> userGrps = tenantUserGrpSlaveStdDataSvc.queryByTenantIds(tenantIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(userGrps)) {
            return Collections.emptyMap();
        }
        return userGrps.stream()
                .collect(Collectors.groupingBy(TenantUserGrpSlaveVO::getTenantId));
    }

    Map<Long, List<TenantUserSlaveVO>> getUsers(final List<Long> tenantIds, final UserQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(TenantUser.ShowColumn.TENANT_SID.getCode()));
        final List<TenantUserSlaveVO> users = tenantUserSlaveStdDataSvc.queryByTenantIds(tenantIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(users)) {
            return Collections.emptyMap();
        }
        return users.stream()
                .collect(Collectors.groupingBy(TenantUserSlaveVO::getTenantId));
    }

    Map<Long, List<TenantUserRoleSlaveVO>> getRoles(final List<Long> tenantIds, final RoleQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(TenantUserRole.ShowColumn.TENANT_SID.getCode()));
        final List<TenantUserRoleSlaveVO> roles = tenantUserRoleSlaveStdDataSvc.queryByTenantIds(tenantIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(roles)) {
            return Collections.emptyMap();
        }
        return roles.stream()
                .collect(Collectors.groupingBy(TenantUserRoleSlaveVO::getTenantId));
    }

    Map<Long, List<TenantUserSysResRoleSlaveVO>> getSysResRoles(final List<Long> tenantIds, final SysResRoleQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(TenantUserSysResRole.ShowColumn.TENANT_SID.getCode()));
        final List<TenantUserSysResRoleSlaveVO> sysResRoles = tenantUserSysResRoleSlaveStdDataSvc.queryByTenantIds(tenantIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResRoles)) {
            return Collections.emptyMap();
        }
        return sysResRoles.stream()
                .collect(Collectors.groupingBy(TenantUserSysResRoleSlaveVO::getTenantId));
    }

    Map<Long, List<TenantUserTmpRoleSlaveVO>> getTmpRoles(final List<Long> tenantIds, final RoleQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(TenantUserTmpRole.ShowColumn.TENANT_SID.getCode()));
        final List<TenantUserTmpRoleSlaveVO> tmpRoles = tenantUserTmpRoleSlaveStdDataSvc.queryByTenantIds(tenantIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpRoles)) {
            return Collections.emptyMap();
        }
        return tmpRoles.stream()
                .collect(Collectors.groupingBy(TenantUserTmpRoleSlaveVO::getTenantId));
    }

    Map<Long, List<TenantUserTmpSysResRoleSlaveVO>> getTmpSysResRoles(final List<Long> tenantIds, final SysResRoleQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(TenantUserTmpSysResRole.ShowColumn.TENANT_SID.getCode()));
        final List<TenantUserTmpSysResRoleSlaveVO> tmpSysResRoles = tenantUserTmpSysResRoleSlaveStdDataSvc.queryByTenantIds(tenantIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpSysResRoles)) {
            return Collections.emptyMap();
        }
        return tmpSysResRoles.stream()
                .collect(Collectors.groupingBy(TenantUserTmpSysResRoleSlaveVO::getTenantId));
    }

    @Override
    public TenantSlaveVO transferToStdDataVO(final Tenant entity) {
        final TenantSlaveVO tenant = new TenantSlaveVO();
        assembleCommonAttrs(tenant, entity);
        tenant.setCode(entity.getCode());
        tenant.setName(entity.getName());
        tenant.setMemo(entity.getMemo());
        tenant.setStatus(entity.getStatus());
        tenant.setLvl(entity.getLvl());
        tenant.setPid(entity.getPid());
        tenant.setRid(entity.getRid());
        return tenant;
    }
}
