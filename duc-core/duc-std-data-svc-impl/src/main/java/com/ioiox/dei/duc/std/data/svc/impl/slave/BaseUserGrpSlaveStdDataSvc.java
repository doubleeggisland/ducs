package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserGrp;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseUserGrpSlaveStdDataSvc<
        R extends RoleSlaveStdVO,
        RR extends SysResRoleSlaveStdVO,
        S extends UserGrpSlaveStdVO<R, RR>,
        E extends UserGrp,
        QP extends UserGrpQueryParam>
        extends BaseDeiSlaveStdDataSvc<S, E> {

    public int countByParam(final QP queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return countByParams(queryParams);
    }

    public List<S> queryByParam(final QP queryParam, final UserGrpQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedRoles()) || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResRoles()))) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<E> entities = findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<Long> userGrpIds = new ArrayList<>(entities.size());
        final List<S> userGrps = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            userGrpIds.add(entity.getSid());
            userGrps.add(transferToStdDataVO(entity));
        });

        final Map<Long, List<R>> groupedRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedRoles())) {
            groupedRoles = getRoles(userGrpIds, queryCfg.getRoleQueryCfg());
        } else {
            groupedRoles = Collections.emptyMap();
        }

        final Map<Long, List<RR>> groupedSysResRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResRoles())) {
            groupedSysResRoles = getSysResRoles(userGrpIds, queryCfg.getSysResRoleQueryCfg());
        } else {
            groupedSysResRoles = Collections.emptyMap();
        }

        userGrps.forEach(userGrp -> {
            userGrp.setRoles(groupedRoles.getOrDefault(userGrp.getId(), Collections.emptyList()));
            userGrp.setSysResRoles(groupedSysResRoles.getOrDefault(userGrp.getId(), Collections.emptyList()));
        });
        return userGrps;
    }

    protected abstract int countByParams(final Map<String, Object> queryParams);

    protected abstract List<E> findByParams(final Map<String, Object> queryParams, final List<String> showColumns);

    protected abstract Map<Long, List<Long>> getRoleIds(final List<Long> userGrpIds);

    protected abstract List<R> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg);

    protected abstract Map<Long, List<Long>> getSysResRoleIds(final List<Long> userGrpIds);

    protected abstract List<RR> querySysResRolesByPks(final List<Long> sysResRoleIds, final SysResRoleQueryCfg queryCfg);

    protected Map<Long, List<R>> getRoles(final List<Long> userGrpIds,
                                          final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userGrpIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedRoleIds = getRoleIds(userGrpIds);
        if (DeiCollectionUtil.isEmpty(groupedRoleIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> roleIds = new HashSet<>(100);
        for (final List<Long> roleIdsOfUserGrp : groupedRoleIds.values()) {
            roleIds.addAll(roleIdsOfUserGrp);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<R> roles = queryRolesByPks(new ArrayList<>(roleIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(roles)) {
            return Collections.emptyMap();
        }
        final Map<Long, R> roleMap =
                roles.stream().collect(Collectors.toMap(R::getId, role -> role));

        final Map<Long, List<R>> groupedRoles = new HashMap<>(userGrpIds.size());
        for (final Long userGrpId : groupedRoleIds.keySet()) {
            final List<Long> roleIdsOfUserGrp = groupedRoleIds.get(userGrpId);
            final List<R> rolesOfUserGrp = new ArrayList<>(roleIdsOfUserGrp.size());
            for (final Long roleIdOfUserGrp : roleIdsOfUserGrp) {
                if (roleMap.containsKey(roleIdOfUserGrp)) {
                    rolesOfUserGrp.add(roleMap.get(roleIdOfUserGrp));
                }
            }
            groupedRoles.put(userGrpId, rolesOfUserGrp);
        }
        return groupedRoles;
    }

    protected Map<Long, List<RR>> getSysResRoles(final List<Long> userGrpIds,
                                                 final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userGrpIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedSysResRoleIds = getSysResRoleIds(userGrpIds);
        if (DeiCollectionUtil.isEmpty(groupedSysResRoleIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> sysResRoleIds = new HashSet<>(100);
        for (final List<Long> sysResRoleIdsOfUserGrp : groupedSysResRoleIds.values()) {
            sysResRoleIds.addAll(sysResRoleIdsOfUserGrp);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<RR> sysResRoles = querySysResRolesByPks(new ArrayList<>(sysResRoleIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResRoles)) {
            return Collections.emptyMap();
        }
        final Map<Long, RR> sysResRoleMap =
                sysResRoles.stream().collect(Collectors.toMap(RR::getId, sysResRole -> sysResRole));

        final Map<Long, List<RR>> groupedSysResRoles = new HashMap<>(userGrpIds.size());
        for (final Long userGrpId : groupedSysResRoleIds.keySet()) {
            final List<Long> sysResRoleIdsOfUserGrp = groupedSysResRoleIds.get(userGrpId);
            final List<RR> sysResRolesOfUserGrp = new ArrayList<>(sysResRoleIdsOfUserGrp.size());
            for (final Long sysResRoleIdOfUserGrp : sysResRoleIdsOfUserGrp) {
                if (sysResRoleMap.containsKey(sysResRoleIdOfUserGrp)) {
                    sysResRolesOfUserGrp.add(sysResRoleMap.get(sysResRoleIdOfUserGrp));
                }
            }
            groupedSysResRoles.put(userGrpId, sysResRolesOfUserGrp);
        }
        return groupedSysResRoles;
    }

    protected void assembleCommonAttrs(final BaseUserGrpSlaveStdVO stdVO, final UserGrp entity) {
        super.assembleCommonAttrs(stdVO, entity);
        stdVO.setCode(entity.getCode());
        stdVO.setName(entity.getName());
        stdVO.setMemo(entity.getMemo());
        stdVO.setStatus(entity.getStatus());
    }
}
