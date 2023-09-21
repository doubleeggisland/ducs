package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseUserSlaveStdDataSvc<
        R extends BaseRoleSlaveVO,
        RR extends BaseSysResRoleSlaveVO,
        TR extends BaseRoleSlaveVO,
        TRR extends BaseSysResRoleSlaveVO,
        UG extends UserGrpSlaveVO<R, RR>,
        T extends UserSlaveVO<R, RR, TR, TRR, UG>,
        E extends BaseUser,
        QP extends UserQueryParam>
        extends BaseDeiSlaveStdDataSvc<T, E> {

    public int countByParam(final QP queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return countByParams(queryParams);
    }

    public List<T> queryByParam(final QP queryParam, final UserQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedRoles())
                || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResRoles())
                || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpRoles())
                || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpSysResRoles())
                || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedUserGrps())
                || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysPrjPrivileges()))) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<E> entities = findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<Long> userIds = new ArrayList<>(entities.size());
        final List<T> users = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            userIds.add(entity.getSid());
            users.add(transferToStdDataVO(entity));
        });

        final Map<Long, List<R>> groupedRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedRoles())) {
            groupedRoles = getRoles(userIds, queryCfg.getRoleQueryCfg());
        } else {
            groupedRoles = Collections.emptyMap();
        }

        final Map<Long, List<RR>> groupedSysResRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResRoles())) {
            groupedSysResRoles = getSysResRoles(userIds, queryCfg.getSysResRoleQueryCfg());
        } else {
            groupedSysResRoles = Collections.emptyMap();
        }

        final Map<Long, List<TR>> groupedTmpRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpRoles())) {
            groupedTmpRoles = getTmpRoles(userIds, queryCfg.getTmpRoleQueryCfg());
        } else {
            groupedTmpRoles = Collections.emptyMap();
        }

        final Map<Long, List<TRR>> groupedTmpSysResRoles;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedTmpSysResRoles())) {
            groupedTmpSysResRoles = getTmpSysResRoles(userIds, queryCfg.getTmpSysResRoleQueryCfg());
        } else {
            groupedTmpSysResRoles = Collections.emptyMap();
        }

        final Map<Long, List<UG>> groupedUserGrps;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedUserGrps())) {
            groupedUserGrps = getUserGrps(userIds, queryCfg.getUserGrpQueryCfg());
        } else {
            groupedUserGrps = Collections.emptyMap();
        }

        final Map<Long, List<UserSysPrjPrivilegeSlaveVO>> groupedSysPrjPrivileges;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysPrjPrivileges())) {
            groupedSysPrjPrivileges = getSysPrjPrivileges(userIds, queryCfg.getSysPrjPrivilegeQueryCfg());
        } else {
            groupedSysPrjPrivileges = Collections.emptyMap();
        }

        users.forEach(user -> {
            user.setRoles(groupedRoles.getOrDefault(user.getId(), Collections.emptyList()));
            user.setSysResRoles(groupedSysResRoles.getOrDefault(user.getId(), Collections.emptyList()));
            user.setTmpRoles(groupedTmpRoles.getOrDefault(user.getId(), Collections.emptyList()));
            user.setTmpSysResRoles(groupedTmpSysResRoles.getOrDefault(user.getId(), Collections.emptyList()));
            user.setUserGrps(groupedUserGrps.getOrDefault(user.getId(), Collections.emptyList()));
            user.setSysPrjPrivileges(groupedSysPrjPrivileges.getOrDefault(user.getId(), Collections.emptyList()));
        });
        return users;
    }

    protected abstract int countByParams(final Map<String, Object> queryParams);

    protected abstract List<E> findByParams(final Map<String, Object> queryParams, final List<String> showColumns);

    protected abstract Map<Long, List<Long>> getRoleIds(final List<Long> userIds);

    protected abstract List<R> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg);

    protected Map<Long, List<R>> getRoles(final List<Long> userIds, final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedRoleIds = getRoleIds(userIds);
        if (DeiCollectionUtil.isEmpty(groupedRoleIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> roleIds = new HashSet<>(100);
        for (final List<Long> roleIdsOfUser : groupedRoleIds.values()) {
            roleIds.addAll(roleIdsOfUser);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<R> roles = queryRolesByPks(new ArrayList<>(roleIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(roles)) {
            return Collections.emptyMap();
        }
        final Map<Long, R> roleMap = roles.stream().collect(Collectors.toMap(R::getId, role -> role));

        final Map<Long, List<R>> groupedRoles = new HashMap<>(userIds.size());
        for (final Long userId : groupedRoleIds.keySet()) {
            final List<Long> roleIdsOfUser = groupedRoleIds.get(userId);
            final List<R> rolesOfUser = new ArrayList<>(roleIdsOfUser.size());
            for (final Long roleIdOfUser : roleIdsOfUser) {
                if (roleMap.containsKey(roleIdOfUser)) {
                    rolesOfUser.add(roleMap.get(roleIdOfUser));
                }
            }
            groupedRoles.put(userId, rolesOfUser);
        }
        return groupedRoles;
    }

    protected abstract Map<Long, List<Long>> getSysResRoleIds(final List<Long> userIds);

    protected abstract List<RR> querySysResRolesByPks(final List<Long> sysResRoleIds, final SysResRoleQueryCfg queryCfg);

    protected Map<Long, List<RR>> getSysResRoles(final List<Long> userIds, final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedSysResRoleIds = getSysResRoleIds(userIds);
        if (DeiCollectionUtil.isEmpty(groupedSysResRoleIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> sysResRoleIds = new HashSet<>(100);
        for (final List<Long> sysResRoleIdsOfUser : groupedSysResRoleIds.values()) {
            sysResRoleIds.addAll(sysResRoleIdsOfUser);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<RR> sysResRoles = querySysResRolesByPks(new ArrayList<>(sysResRoleIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResRoles)) {
            return Collections.emptyMap();
        }
        final Map<Long, RR> sysResRoleMap = sysResRoles.stream().collect(Collectors.toMap(RR::getId, sysResRole -> sysResRole));

        final Map<Long, List<RR>> groupedSysResRoles = new HashMap<>(userIds.size());
        for (final Long userId : groupedSysResRoleIds.keySet()) {
            final List<Long> sysResRoleIdsOfUser = groupedSysResRoleIds.get(userId);
            final List<RR> sysResRolesOfUser = new ArrayList<>(sysResRoleIdsOfUser.size());
            for (final Long sysResRoleIdOfUser : sysResRoleIdsOfUser) {
                if (sysResRoleMap.containsKey(sysResRoleIdOfUser)) {
                    sysResRolesOfUser.add(sysResRoleMap.get(sysResRoleIdOfUser));
                }
            }
            groupedSysResRoles.put(userId, sysResRolesOfUser);
        }
        return groupedSysResRoles;
    }

    protected abstract Map<Long, List<Long>> getTmpRoleIds(final List<Long> userIds);

    protected abstract List<TR> queryTmpRolesByPks(final List<Long> tmpRoleIds, final RoleQueryCfg queryCfg);

    protected Map<Long, List<TR>> getTmpRoles(final List<Long> userIds, final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedTmpRoleIds = getTmpRoleIds(userIds);
        if (DeiCollectionUtil.isEmpty(groupedTmpRoleIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> tmpRoleIds = new HashSet<>(100);
        for (final List<Long> tmpRoleIdsOfUser : groupedTmpRoleIds.values()) {
            tmpRoleIds.addAll(tmpRoleIdsOfUser);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<TR> tmpRoles = queryTmpRolesByPks(new ArrayList<>(tmpRoleIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpRoles)) {
            return Collections.emptyMap();
        }
        final Map<Long, TR> tmpRoleMap = tmpRoles.stream().collect(Collectors.toMap(TR::getId, tmpRole -> tmpRole));

        final Map<Long, List<TR>> groupedTmpRoles = new HashMap<>(userIds.size());
        for (final Long userId : groupedTmpRoleIds.keySet()) {
            final List<Long> tmpRoleIdsOfUser = groupedTmpRoleIds.get(userId);
            final List<TR> tmpRolesOfUser = new ArrayList<>(tmpRoleIdsOfUser.size());
            for (final Long tmpRoleIdOfUser : tmpRoleIdsOfUser) {
                if (tmpRoleMap.containsKey(tmpRoleIdOfUser)) {
                    tmpRolesOfUser.add(tmpRoleMap.get(tmpRoleIdOfUser));
                }
            }
            groupedTmpRoles.put(userId, tmpRolesOfUser);
        }
        return groupedTmpRoles;
    }

    protected abstract Map<Long, List<Long>> getTmpSysResRoleIds(final List<Long> userIds);

    protected abstract List<TRR> queryTmpSysResRolesByPks(final List<Long> tmpSysResRoleIds, final SysResRoleQueryCfg queryCfg);

    protected Map<Long, List<TRR>> getTmpSysResRoles(final List<Long> userIds, final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedTmpSysResRoleIds = getTmpSysResRoleIds(userIds);
        if (DeiCollectionUtil.isEmpty(groupedTmpSysResRoleIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> tmpSysResRoleIds = new HashSet<>(100);
        for (final List<Long> tmpSysResRoleIdsOfUser : groupedTmpSysResRoleIds.values()) {
            tmpSysResRoleIds.addAll(tmpSysResRoleIdsOfUser);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<TRR> tmpSysResRoles = queryTmpSysResRolesByPks(new ArrayList<>(tmpSysResRoleIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpSysResRoles)) {
            return Collections.emptyMap();
        }
        final Map<Long, TRR> tmpSysResRoleMap = tmpSysResRoles.stream().collect(Collectors.toMap(TRR::getId, tmpSysResRole -> tmpSysResRole));

        final Map<Long, List<TRR>> groupedTmpSysResRoles = new HashMap<>(userIds.size());
        for (final Long userId : groupedTmpSysResRoleIds.keySet()) {
            final List<Long> tmpSysResRoleIdsOfUser = groupedTmpSysResRoleIds.get(userId);
            final List<TRR> tmpSysResRolesOfUser = new ArrayList<>(tmpSysResRoleIdsOfUser.size());
            for (final Long tmpSysResRoleIdOfUser : tmpSysResRoleIdsOfUser) {
                if (tmpSysResRoleMap.containsKey(tmpSysResRoleIdOfUser)) {
                    tmpSysResRolesOfUser.add(tmpSysResRoleMap.get(tmpSysResRoleIdOfUser));
                }
            }
            groupedTmpSysResRoles.put(userId, tmpSysResRolesOfUser);
        }
        return groupedTmpSysResRoles;
    }

    protected abstract Map<Long, List<Long>> getUserGrpIds(final List<Long> userIds);

    protected abstract List<UG> queryUserGrpsByPks(final List<Long> userGrpIds, final UserGrpQueryCfg queryCfg);

    protected Map<Long, List<UG>> getUserGrps(final List<Long> userIds, final UserGrpQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedUserGrpIds = getUserGrpIds(userIds);
        if (DeiCollectionUtil.isEmpty(groupedUserGrpIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> userGrpIds = new HashSet<>(100);
        for (final List<Long> userGrpIdsOfUser : groupedUserGrpIds.values()) {
            userGrpIds.addAll(userGrpIdsOfUser);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<UG> userGrps = queryUserGrpsByPks(new ArrayList<>(userGrpIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(userGrps)) {
            return Collections.emptyMap();
        }
        final Map<Long, UG> userGrpMap = userGrps.stream().collect(Collectors.toMap(UG::getId, userGrp -> userGrp));

        final Map<Long, List<UG>> groupedUserGrps = new HashMap<>(userIds.size());
        for (final Long userId : groupedUserGrpIds.keySet()) {
            final List<Long> userGrpIdsOfUser = groupedUserGrpIds.get(userId);
            final List<UG> userGrpsOfUser = new ArrayList<>(userGrpIdsOfUser.size());
            for (final Long userGrpIdOfUser : userGrpIdsOfUser) {
                if (userGrpMap.containsKey(userGrpIdOfUser)) {
                    userGrpsOfUser.add(userGrpMap.get(userGrpIdOfUser));
                }
            }
            groupedUserGrps.put(userId, userGrpsOfUser);
        }
        return groupedUserGrps;
    }

    protected abstract List<UserSysPrjPrivilegeSlaveVO> querySysPrjPrivilegesByUserIds(final List<Long> userIds,
                                                                                       final UserSysPrjPrivilegeQueryCfg queryCfg);

    protected Map<Long, List<UserSysPrjPrivilegeSlaveVO>> getSysPrjPrivileges(final List<Long> userIds,
                                                                              final UserSysPrjPrivilegeQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(UserSysPrjPrivilege.ShowColumn.USER_SID.getCode()));
        final List<UserSysPrjPrivilegeSlaveVO> sysPrjPrivileges = querySysPrjPrivilegesByUserIds(userIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysPrjPrivileges)) {
            return Collections.emptyMap();
        }
        return sysPrjPrivileges.stream()
                .collect(Collectors.groupingBy(UserSysPrjPrivilegeSlaveVO::getUserId));
    }

    protected void assembleCommonAttrs(final BaseUserSlaveVO stdVO, final BaseUser entity) {
        super.assembleCommonAttrs(stdVO, entity);
        stdVO.setUsername(entity.getUsername());
        stdVO.setNickName(entity.getNickName());
        stdVO.setMobile(entity.getMobile());
        stdVO.setEmail(entity.getEmail());
        stdVO.setStatus(entity.getStatus());
        stdVO.setPwd(entity.getPwd());
        stdVO.setAvatarUrl(entity.getAvatarUrl());
    }
}
