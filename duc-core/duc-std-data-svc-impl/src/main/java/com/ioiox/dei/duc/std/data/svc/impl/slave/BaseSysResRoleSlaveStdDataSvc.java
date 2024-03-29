package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.BaseSysResRole;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseSysResRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseSysResRoleSlaveStdDataSvc<
        R extends BaseSysResRoleSlaveVO,
        E extends BaseSysResRole,
        QP extends SimpleRoleQueryParam>
        extends SimpleRoleSlaveStdDataSvc<R, E> {

    @Autowired
    @Qualifier("sysResSlaveStdDataSvc")
    private SysResSlaveStdDataSvc sysResSlaveStdDataSvc;

    public int countByParam(final QP queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return countByParams(queryParams);
    }

    public List<R> queryByParam(final QP queryParam,
                                final SysResRoleQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResources())) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<E> entities = findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<Long> sysResRoleIds = new ArrayList<>(entities.size());
        final List<R> sysResRoles = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            sysResRoleIds.add(entity.getSid());
            sysResRoles.add(transferToStdDataVO(entity));
        });

        final Map<Long, List<SysResSlaveVO>> groupedSysResources;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResources())) {
            groupedSysResources = getSysResources(sysResRoleIds, queryCfg.getSysResQueryCfg());
        } else {
            groupedSysResources = Collections.emptyMap();
        }

        sysResRoles.forEach(sysResRole ->
                        sysResRole.setSysResources(groupedSysResources.getOrDefault(sysResRole.getId(), Collections.emptyList())));
        return sysResRoles;
    }

    protected abstract int countByParams(final Map<String, Object> queryParams);

    protected abstract List<E> findByParams(final Map<String, Object> queryParams, final List<String> showColumns);

    protected abstract Map<Long, List<Long>> getSysResIds(final List<Long> sysResRoleIds);

    protected Map<Long, List<SysResSlaveVO>> getSysResources(final List<Long> sysResRoleIds,
                                                             final StdDataQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysResRoleIds)) {
            return Collections.emptyMap();
        }
        final Map<Long, List<Long>> groupedSysResIds = getSysResIds(sysResRoleIds);
        if (DeiCollectionUtil.isEmpty(groupedSysResIds)) {
            return Collections.emptyMap();
        }
        final Set<Long> sysResIds = new HashSet<>(100);
        for (final List<Long> sysResIdsOfRole : groupedSysResIds.values()) {
            sysResIds.addAll(sysResIdsOfRole);
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<SysResSlaveVO> sysResources =
                sysResSlaveStdDataSvc.queryByPks(new ArrayList<>(sysResIds), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResources)) {
            return Collections.emptyMap();
        }
        final Map<Long, SysResSlaveVO> sysResMap =
                sysResources.stream().collect(Collectors.toMap(SysResSlaveVO::getId, sysRes -> sysRes));

        final Map<Long, List<SysResSlaveVO>> groupedSysResources = new HashMap<>(sysResRoleIds.size());
        for (final Long roleId : groupedSysResIds.keySet()) {
            final List<Long> sysResIdsOfRole = groupedSysResIds.get(roleId);
            final List<SysResSlaveVO> sysResourcesOfRole = new ArrayList<>(sysResIdsOfRole.size());
            for (final Long sysResIdOfRole : sysResIdsOfRole) {
                if (sysResMap.containsKey(sysResIdOfRole)) {
                    sysResourcesOfRole.add(sysResMap.get(sysResIdOfRole));
                }
            }
            groupedSysResources.put(roleId, sysResourcesOfRole);
        }
        return groupedSysResources;
    }
}
