package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysApiSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseRoleSlaveStdDataSvc<
        R extends BaseRoleSlaveStdVO,
        E extends Role,
        QP extends RoleQueryParam>
        extends CommonRoleSlaveStdDataSvc<R, E> {

    @Autowired
    @Qualifier("menuSlaveStdDataSvc")
    private MenuSlaveStdDataSvc menuSlaveStdDataSvc;

    @Autowired
    @Qualifier("sysApiSlaveStdDataSvc")
    private SysApiSlaveStdDataSvc sysApiSlaveStdDataSvc;

    public int countByParam(final QP queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return countByParams(queryParams);
    }

    public List<R> queryByParam(final QP queryParam, final RoleQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedMenus()) || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApis()))) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<E> entities = findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<Long> roleIds = new ArrayList<>(entities.size());
        final List<R> roles = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            roleIds.add(entity.getSid());
            roles.add(transferToStdDataVO(entity));
        });

        final Map<Long, List<MenuSlaveStdVO>> groupedMenus;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedMenus())) {
            groupedMenus = getMenus(roleIds, queryCfg.getMenuQueryCfg());
        } else {
            groupedMenus = Collections.emptyMap();
        }

        final Map<Long, List<SysApiSlaveStdVO>> groupedSysApis;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedMenus())) {
            groupedSysApis = getSysApis(roleIds, queryCfg.getSysApiQueryCfg());
        } else {
            groupedSysApis = Collections.emptyMap();
        }

        roles.forEach(role -> {
            assembleMenus(role, groupedMenus.getOrDefault(role.getId(), Collections.emptyList()));
            assembleSysApis(role, groupedSysApis.getOrDefault(role.getId(), Collections.emptyList()));
        });
        return roles;
    }

    protected abstract int countByParams(final Map<String, Object> queryParams);

    protected abstract List<E> findByParams(final Map<String, Object> queryParams, final List<String> showColumns);

    protected abstract Map<Long, List<Long>> getMenuIds(final List<Long> roleIds);

    protected abstract Map<Long, List<Long>> getSysApiIds(final List<Long> roleIds);

    protected abstract void assembleMenus(final R role, final List<MenuSlaveStdVO> menus);

    protected abstract void assembleSysApis(final R role, final List<SysApiSlaveStdVO> sysApis);

    protected Map<Long, List<MenuSlaveStdVO>> getMenus(final List<Long> roleIds,
                                                       final MenuQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedMenuIds = getMenuIds(roleIds);
        if (DeiCollectionUtil.isEmpty(groupedMenuIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> menuIds = new HashSet<>(100);
        for (final List<Long> menuIdsOfRole : groupedMenuIds.values()) {
            menuIds.addAll(menuIdsOfRole);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<MenuSlaveStdVO> menus =
                menuSlaveStdDataSvc.queryByPks(new ArrayList<>(menuIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(menus)) {
            return Collections.emptyMap();
        }
        final Map<Long, MenuSlaveStdVO> menuMap =
                menus.stream().collect(Collectors.toMap(MenuSlaveStdVO::getId, menu -> menu));

        final Map<Long, List<MenuSlaveStdVO>> groupedMenus = new HashMap<>(roleIds.size());
        for (final Long roleId : groupedMenuIds.keySet()) {
            final List<Long> menuIdsOfRole = groupedMenuIds.get(roleId);
            final List<MenuSlaveStdVO> menusOfRole = new ArrayList<>(menuIdsOfRole.size());
            for (final Long menuIdOfRole : menuIdsOfRole) {
                if (menuMap.containsKey(menuIdOfRole)) {
                    menusOfRole.add(menuMap.get(menuIdOfRole));
                }
            }
            groupedMenus.put(roleId, menusOfRole);
        }
        return groupedMenus;
    }

    protected Map<Long, List<SysApiSlaveStdVO>> getSysApis(final List<Long> roleIds,
                                                           final StdDataQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedSysApiIds = getSysApiIds(roleIds);
        if (DeiCollectionUtil.isEmpty(groupedSysApiIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> sysApiIds = new HashSet<>(200);
        for (final List<Long> sysApiIdsOfRole : groupedSysApiIds.values()) {
            sysApiIds.addAll(sysApiIdsOfRole);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<SysApiSlaveStdVO> sysApis =
                sysApiSlaveStdDataSvc.queryByPks(new ArrayList<>(sysApiIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysApis)) {
            return Collections.emptyMap();
        }
        final Map<Long, SysApiSlaveStdVO> sysApiMap =
                sysApis.stream().collect(Collectors.toMap(SysApiSlaveStdVO::getId, sysApi -> sysApi));

        final Map<Long, List<SysApiSlaveStdVO>> groupedSysApis = new HashMap<>(roleIds.size());
        for (final Long roleId : groupedSysApiIds.keySet()) {
            final List<Long> sysApiIdsOfRole = groupedSysApiIds.get(roleId);
            final List<SysApiSlaveStdVO> sysApisOfRole = new ArrayList<>(sysApiIdsOfRole.size());
            for (final Long sysApiIdOfRole : sysApiIdsOfRole) {
                if (sysApiMap.containsKey(sysApiIdOfRole)) {
                    sysApisOfRole.add(sysApiMap.get(sysApiIdOfRole));
                }
            }
            groupedSysApis.put(roleId, sysApisOfRole);
        }
        return groupedSysApis;
    }
}
