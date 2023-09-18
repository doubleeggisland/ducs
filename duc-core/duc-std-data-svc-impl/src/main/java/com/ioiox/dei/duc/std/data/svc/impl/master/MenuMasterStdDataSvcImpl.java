package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.beans.model.master.MenuUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.MenuUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.MenuUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryParam;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.model.master.MenuDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterStdVO;
import com.ioiox.dei.duc.beans.model.master.MenuSysApiMappingDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuSysApiMappingMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.db.service.master.MenuMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.MenuMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.MenuSysApiMappingMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("menuMasterStdDataSvc")
public class MenuMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<MenuMasterStdVO, MenuUpdatableObj, Menu>
        implements MenuMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(MenuMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("menuMasterDbSvc")
    private MenuMasterDbSvc menuMasterDbSvc;

    @Autowired
    @Qualifier("menuSysApiMappingMasterStdDataSvc")
    private MenuSysApiMappingMasterStdDataSvc menuSysApiMappingMasterStdDataSvc;

    @Autowired
    @Qualifier("menuSlaveStdDataSvc")
    private MenuSlaveStdDataSvc menuSlaveStdDataSvc;

    private final MenuUpdatableAttrsAnalyser analyser = new MenuUpdatableAttrsAnalyser();

    @Override
    public Long save(final MenuMasterStdVO menu) {
        if (Objects.isNull(menu)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final Menu newEntity = toNewEntity(menu);
        newEntity.setDefaultValueIfNeed();
        menuMasterDbSvc.dbInsert(newEntity);

        final Long menuId = newEntity.getSid();
        syncSysApiMappings(menu.getSysApiMappings(), null, menuId, menu.getUpdatedBy());
        return menuId;
    }

    private MenuSlaveVO getExistingMenu(final Long id) {
        return menuSlaveStdDataSvc.getByPk(id,
                new MenuQueryCfg.Builder()
                        .needSysApiMappings(DeiGlobalConstant.FLAG_YES)
                        .sysApiMappingQueryCfg(new MenuSysApiMappingQueryCfg.Builder()
                                .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                        MenuSysApiMapping.ShowColumn.MENU_SID.getCode(), MenuSysApiMapping.ShowColumn.SYS_API_SID.getCode(),
                                        MenuSysApiMapping.ShowColumn.INTERACT_FORM.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                                .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                        Menu.ShowColumn.CODE.getCode(), Menu.ShowColumn.NAME.getCode(),
                                        Menu.ShowColumn.PARENT_SID.getCode(), Menu.ShowColumn.LEVEL.getCode(),
                                        Menu.ShowColumn.ROUTE_PATH.getCode(), Menu.ShowColumn.COMPONENT_URL.getCode(),
                                        Menu.ShowColumn.REDIRECT_PATH.getCode(), Menu.ShowColumn.IS_HIDDEN.getCode(),
                                        Menu.ShowColumn.IS_CACHE.getCode(), Menu.ShowColumn.ICON.getCode(),
                                        Menu.ShowColumn.STATUS.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                                .build())
                        .build());
    }

    @Override
    public boolean update(final MenuMasterStdVO menu) {
        if (Objects.isNull(menu) || Objects.isNull(menu.getId())) {
            throw new DeiServiceException("Please choose a menu to update!");
        }
        final MenuSlaveVO existingMenu = getExistingMenu(menu.getId());
        if (Objects.isNull(existingMenu)) {
            throw new DeiServiceException(String.format("Menu doesn't exist =====> id: %s", menu.getId()));
        }
        return update(menu, existingMenu);
    }

    public boolean update(final MenuMasterStdVO menu,
                          final MenuSlaveVO existingMenu) {
        final int syncRows =
                syncSysApiMappings(menu.getSysApiMappings(), existingMenu.getSysApiMappings(), existingMenu.getId(), menu.getUpdatedBy());

        final MenuUpdateCtx updateCtx = analyser.analyseUpdatedAttrs(menu, existingMenu);
        final MenuUpdatableObj updatableObj = updateCtx.getUpdatableObj();
        if (updatableObj.attrsNotUpdated()) {
            if (syncRows > DeiGlobalConstant.ZERO) {
                updatableObj.updateLastModifiedTime();
            }
        }

        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                if (updatableObj.attrsUpdated()) {
                    final Map<String, String> updateSummary = updatableObj.updateSummary();
                    log.info(String.format("update Menu =====> id: %s, updateSummary: %s", existingMenu.getId(), JsonUtil.toJsonStr(updateSummary)));
                } else {
                    log.info(String.format("update Menu lastModifiedTime =====> id: %s, lastUpdateTime: %s",
                            existingMenu.getId(), updatableObj.formatLastModifiedTime()));
                }
            }
            final Menu example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingMenu, menu);
            final int updatedRows = menuMasterDbSvc.dbUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("Menu has been updated by others =====> id: %s, versionNum: %s",
                        existingMenu.getId(), existingMenu.getVersionNum()));
            }
        }
        return updated;
    }

    private int syncSysApiMappings(final List<MenuSysApiMappingMasterVO> sysApiMappings,
                                   final List<MenuSysApiMappingSlaveStdVO> existingSysApiMappings,
                                   final Long menuId,
                                   final String operator) {
        if (DeiCollectionUtil.isNotEmpty(sysApiMappings)
                && Objects.nonNull(menuId)) {
            sysApiMappings.forEach(sysApiMapping -> {
                if (Objects.isNull(sysApiMapping.getMenuId())) {
                    sysApiMapping.setMenuId(menuId);
                }
                if (StringUtils.isNotBlank(operator)) {
                    sysApiMapping.setCreatedBy(operator);
                    sysApiMapping.setUpdatedBy(operator);
                }
            });
        }
        return menuSysApiMappingMasterStdDataSvc.sync(sysApiMappings, existingSysApiMappings);
    }

    @Override
    public int remove(final MenuDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }

        final List<MenuSlaveVO> existingMenus = queryExistingMenus(delParam);
        if (DeiCollectionUtil.isEmpty(existingMenus)) {
            throw new DeiServiceException(String.format("Cannot find any menus as per delParam =====> %s", JsonUtil.toJsonStr(delParam)));
        }
        menuSysApiMappingMasterStdDataSvc.remove(new MenuSysApiMappingDelParam.Builder()
                .menuIds(existingMenus.stream().map(MenuSlaveVO::getId).collect(Collectors.toList()))
                .build());
        return menuMasterDbSvc.deleteByParams(deleteParams);
    }

    private List<MenuSlaveVO> queryExistingMenus(final MenuDelParam delParam) {
        final MenuQueryParam queryParam = new MenuQueryParam.Builder()
                .pids(delParam.getPids())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return menuSlaveStdDataSvc.queryByParam(queryParam,
                new MenuQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    public void remove(final List<Long> menuIds) {
        if (DeiCollectionUtil.isEmpty(menuIds)) {
            return;
        }
        final MenuDelParam delParam = new MenuDelParam.Builder()
                .pks(menuIds)
                .build();
        remove(delParam);
    }

    @Override
    public Menu toNewEntity(final MenuMasterStdVO masterStdVO) {
        final Menu newEntity = new Menu();
        assembleCommonAttrsOnInsert(newEntity, masterStdVO);
        newEntity.setCode(masterStdVO.getCode());
        newEntity.setName(masterStdVO.getName());
        newEntity.setPid(masterStdVO.getPid());
        newEntity.setLvl(masterStdVO.getLvl());
        newEntity.setRoutePath(masterStdVO.getRoutePath());
        newEntity.setComponentUrl(masterStdVO.getComponentUrl());
        newEntity.setRedirectPath(masterStdVO.getRedirectPath());
        newEntity.setIsHidden(masterStdVO.getIsHidden());
        newEntity.setIsCache(masterStdVO.getIsCache());
        newEntity.setIcon(masterStdVO.getIcon());
        newEntity.setStatus(masterStdVO.getStatus());
        newEntity.setSysPrjSid(masterStdVO.getSysPrjId());
        return newEntity;
    }

    @Override
    public Menu toUpdatableObj(final MenuUpdatableObj updatableVO) {
        final Menu example = new Menu();
        assembleCommonAttrs(example, updatableVO);

        if (Objects.nonNull(updatableVO.getCode())) {
            example.setCode(updatableVO.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getName())) {
            example.setName(updatableVO.getName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getPid())) {
            example.setPid(updatableVO.getPid().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getLvl())) {
            example.setLvl(updatableVO.getLvl().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getRoutePath())) {
            example.setRoutePath(updatableVO.getRoutePath().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getComponentUrl())) {
            example.setComponentUrl(updatableVO.getComponentUrl().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getRedirectPath())) {
            example.setRedirectPath(updatableVO.getRedirectPath().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getIsHidden())) {
            example.setIsHidden(updatableVO.getIsHidden().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getIsCache())) {
            example.setIsCache(updatableVO.getIsCache().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getIcon())) {
            example.setIcon(updatableVO.getIcon().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getStatus())) {
            example.setStatus(updatableVO.getStatus().getNewVal());
        }
        return example;
    }
}
