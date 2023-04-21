package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSysApiMappingSlaveStdDataSvc;
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
    @Qualifier("menuSysApiMappingSlaveStdDataSvc")
    private MenuSysApiMappingSlaveStdDataSvc menuSysApiMappingSlaveStdDataSvc;

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
                && (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedMenus())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApiMappings()))) {
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

        final Map<Long, List<MenuSysApiMappingSlaveStdVO>> groupedSysApiMappings;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApiMappings())) {
            groupedSysApiMappings = getSysApiMappings(roleIds, queryCfg.getSysApiMappingQueryCfg());
        } else {
            groupedSysApiMappings = Collections.emptyMap();
        }

        roles.forEach(role -> {
            assembleMenus(role, groupedMenus.getOrDefault(role.getId(), Collections.emptyList()));
            final List<MenuSysApiMappingSlaveStdVO> sysApiMappingsOfRole =
                    groupedSysApiMappings.getOrDefault(role.getId(), Collections.emptyList());
            assembleSysApiMappings(role, sysApiMappingsOfRole);
            final List<SysApiSlaveStdVO> sysApisOfRole = getSysApis(sysApiMappingsOfRole);
            assembleSysApis(role, sysApisOfRole);
        });
        return roles;
    }

    private List<SysApiSlaveStdVO> getSysApis(final List<MenuSysApiMappingSlaveStdVO> sysApiMappingsOfRole) {
        if (DeiCollectionUtil.isEmpty(sysApiMappingsOfRole)) {
            return Collections.emptyList();
        }
        final Map<Long, SysApiSlaveStdVO> sysApiMap = new HashMap<>(sysApiMappingsOfRole.size());
        for (final MenuSysApiMappingSlaveStdVO sysApiMapping : sysApiMappingsOfRole) {
            if (Objects.isNull(sysApiMapping.getSysApi())) {
                continue;
            }
            final SysApiSlaveStdVO sysApi = sysApiMapping.getSysApi();
            if (sysApiMap.containsKey(sysApi.getId())) {
                continue;
            }
            sysApiMap.put(sysApi.getId(), sysApi);
        }
        return new ArrayList<>(sysApiMap.values());
    }

    protected abstract int countByParams(final Map<String, Object> queryParams);

    protected abstract List<E> findByParams(final Map<String, Object> queryParams, final List<String> showColumns);

    protected abstract Map<Long, List<Long>> getMenuIds(final List<Long> roleIds);

    protected abstract Map<Long, List<Long>> getSysApiMappingIds(final List<Long> roleIds);

    protected abstract void assembleMenus(final R role, final List<MenuSlaveStdVO> menus);

    protected abstract void assembleSysApiMappings(final R role, final List<MenuSysApiMappingSlaveStdVO> sysApiMappings);

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
        final Set<Long> menuIds = new LinkedHashSet<>(64);
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

    protected Map<Long, List<MenuSysApiMappingSlaveStdVO>> getSysApiMappings(final List<Long> roleIds,
                                                                             final MenuSysApiMappingQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedMappingIds = getSysApiMappingIds(roleIds);
        if (DeiCollectionUtil.isEmpty(groupedMappingIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> sysApiMappingIds = new LinkedHashSet<>(128);
        for (final List<Long> mappingIdsOfRole : groupedMappingIds.values()) {
            sysApiMappingIds.addAll(mappingIdsOfRole);
        }
        addShowColumnsIfNeeded(queryCfg, Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(), MenuSysApiMapping.ShowColumn.MENU_SID.getCode()));
        final List<MenuSysApiMappingSlaveStdVO> sysApiMappings =
                menuSysApiMappingSlaveStdDataSvc.queryByPks(new ArrayList<>(sysApiMappingIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            return Collections.emptyMap();
        }
        final Map<Long, MenuSysApiMappingSlaveStdVO> sysApiMappingMap =
                sysApiMappings.stream().collect(Collectors.toMap(MenuSysApiMappingSlaveStdVO::getId, item -> item));
        final Map<Long, List<MenuSysApiMappingSlaveStdVO>> groupedSysApiMappings = new HashMap<>(roleIds.size());
        for (final Long roleId : groupedMappingIds.keySet()) {
            final List<Long> sysApiMappingIdsOfRole = groupedMappingIds.get(roleId);
            final List<MenuSysApiMappingSlaveStdVO> sysApiMappingsOfRole = new ArrayList<>(sysApiMappingIdsOfRole.size());
            for (final Long sysApiMappingIdOfRole : sysApiMappingIdsOfRole) {
                if (sysApiMappingMap.containsKey(sysApiMappingIdOfRole)) {
                    sysApiMappingsOfRole.add(sysApiMappingMap.get(sysApiMappingIdOfRole));
                }
            }
            groupedSysApiMappings.put(roleId, sysApiMappingsOfRole);
        }
        return groupedSysApiMappings;
    }
}