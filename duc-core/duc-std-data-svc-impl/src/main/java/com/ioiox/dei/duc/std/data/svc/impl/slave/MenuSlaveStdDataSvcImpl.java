package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryParam;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.db.service.slave.MenuSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSysApiMappingSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("menuSlaveStdDataSvc")
public class MenuSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<MenuSlaveVO, Menu>
        implements MenuSlaveStdDataSvc {

    @Autowired
    @Qualifier("menuSlaveDbSvc")
    private MenuSlaveDbSvc menuSlaveDbSvc;

    @Autowired
    @Qualifier("menuSysApiMappingSlaveStdDataSvc")
    private MenuSysApiMappingSlaveStdDataSvc menuSysApiMappingSlaveStdDataSvc;

    @Override
    public int countByParam (final MenuQueryParam queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return menuSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    public List<MenuSlaveVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                              final MenuQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysPrjIds)) {
            return Collections.emptyList();
        }
        final MenuQueryParam queryParam = new MenuQueryParam.Builder()
                .sysPrjIds(sysPrjIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public MenuSlaveVO getByPk(final Long menuId,
                               final MenuQueryCfg queryCfg) {
        if (Objects.isNull(menuId)) {
            return null;
        }
        final List<MenuSlaveVO> menus =
                queryByPks(Collections.singletonList(menuId), queryCfg);
        if (DeiCollectionUtil.isEmpty(menus)) {
            return null;
        }
        return menus.get(0);
    }

    @Override
    public List<MenuSlaveVO> queryByPks(final List<Long> menuIds,
                                        final MenuQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        final MenuQueryParam queryParam = new MenuQueryParam.Builder()
                .pks(menuIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<MenuSlaveVO> queryByParam(final MenuQueryParam queryParam,
                                          final MenuQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApiMappings())) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<Menu> entities = menuSlaveDbSvc.findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<MenuSlaveVO> menus = new ArrayList<>(entities.size());
        final List<Long> menuIds = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            menuIds.add(entity.getSid());
            menus.add(transferToStdDataVO(entity));
        });

        final Map<Long, List<MenuSysApiMappingSlaveStdVO>> groupedSysApiMappings;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApiMappings())) {
            groupedSysApiMappings = getSysApiMappings(menuIds, queryCfg.getSysApiMappingQueryCfg());
        } else {
            groupedSysApiMappings = Collections.emptyMap();
        }
        menus.forEach(menu ->
                menu.setSysApiMappings(groupedSysApiMappings.getOrDefault(menu.getId(), Collections.emptyList())));
        return menus;
    }

    private Map<Long, List<MenuSysApiMappingSlaveStdVO>> getSysApiMappings(final List<Long> menuIds,
                                                                           final MenuSysApiMappingQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(MenuSysApiMapping.ShowColumn.MENU_SID.getCode()));
        final List<MenuSysApiMappingSlaveStdVO> sysApiMappings =
                menuSysApiMappingSlaveStdDataSvc.queryByMenuIds(menuIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            return Collections.emptyMap();
        }
        return sysApiMappings.stream().filter(sysApiMapping -> Objects.nonNull(sysApiMapping.getMenuId()))
                .collect(Collectors.groupingBy(MenuSysApiMappingSlaveStdVO::getMenuId));
    }

    @Override
    public MenuSlaveVO transferToStdDataVO(final Menu entity) {
        final MenuSlaveVO stdVO = new MenuSlaveVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setCode(entity.getCode());
        stdVO.setName(entity.getName());
        stdVO.setPid(entity.getPid());
        stdVO.setLvl(entity.getLvl());
        stdVO.setRoutePath(entity.getRoutePath());
        stdVO.setComponentUrl(entity.getComponentUrl());
        stdVO.setRedirectPath(entity.getRedirectPath());
        stdVO.setIsHidden(entity.getIsHidden());
        stdVO.setIsCache(entity.getIsCache());
        stdVO.setIcon(entity.getIcon());
        stdVO.setStatus(entity.getStatus());
        stdVO.setSysPrjId(entity.getSysPrjSid());
        return stdVO;
    }
}
