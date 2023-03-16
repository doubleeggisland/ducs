package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveStdVO;

public class MenuUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<MenuMasterStdVO, MenuSlaveStdVO, MenuUpdatableObj, MenuUpdateCtx> {

    @Override
    public MenuUpdateCtx analyseUpdatedAttrs(final MenuMasterStdVO menu, final MenuSlaveStdVO existingMenu) {
        final MenuUpdateCtx updateCtx = new MenuUpdateCtx();
        updateCtx.setUpdatableObj(new MenuUpdatableObj());
        analyseUpdatedAttrs(menu, existingMenu, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final MenuMasterStdVO menu, final MenuSlaveStdVO existingMenu, final MenuUpdateCtx updateCtx) {
        if (UpdatableVO.modified(existingMenu.getCode(), menu.getCode())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Menu.ShowColumn.CODE.getCode(), existingMenu.getCode(), menu.getCode()));
        }
        if (UpdatableVO.modified(existingMenu.getName(), menu.getName())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Menu.ShowColumn.NAME.getCode(), existingMenu.getName(), menu.getName()));
        }
        if (UpdatableVO.modified(existingMenu.getPid(), menu.getPid())) {
            updateCtx.getUpdatableObj().setPid(new UpdatableAttr<>(Menu.ShowColumn.PARENT_SID.getCode(), existingMenu.getPid(), menu.getPid()));
        }
        if (UpdatableVO.modified(existingMenu.getLvl(), menu.getLvl())) {
            updateCtx.getUpdatableObj().setLvl(new UpdatableAttr<>(Menu.ShowColumn.LEVEL.getCode(), existingMenu.getLvl(), menu.getLvl()));
        }
        if (UpdatableVO.modified(existingMenu.getRoutePath(), menu.getRoutePath())) {
            updateCtx.getUpdatableObj().setRoutePath(new UpdatableAttr<>(Menu.ShowColumn.ROUTE_PATH.getCode(), existingMenu.getRoutePath(), menu.getRoutePath()));
        }
        if (UpdatableVO.modified(existingMenu.getComponentUrl(), menu.getComponentUrl())) {
            updateCtx.getUpdatableObj().setComponentUrl(new UpdatableAttr<>(Menu.ShowColumn.COMPONENT_URL.getCode(), existingMenu.getComponentUrl(), menu.getComponentUrl()));
        }
        if (UpdatableVO.modified(existingMenu.getRedirectPath(), menu.getRedirectPath())) {
            updateCtx.getUpdatableObj().setRedirectPath(new UpdatableAttr<>(Menu.ShowColumn.REDIRECT_PATH.getCode(), existingMenu.getRedirectPath(), menu.getRedirectPath()));
        }
        if (UpdatableVO.modified(existingMenu.getIsHidden(), menu.getIsHidden())) {
            updateCtx.getUpdatableObj().setIsHidden(new UpdatableAttr<>(Menu.ShowColumn.IS_HIDDEN.getCode(), existingMenu.getIsHidden(), menu.getIsHidden()));
        }
        if (UpdatableVO.modified(existingMenu.getIsCache(), menu.getIsCache())) {
            updateCtx.getUpdatableObj().setIsCache(new UpdatableAttr<>(Menu.ShowColumn.IS_CACHE.getCode(), existingMenu.getIsCache(), menu.getIsCache()));
        }
        if (UpdatableVO.modified(existingMenu.getIcon(), menu.getIcon())) {
            updateCtx.getUpdatableObj().setIcon(new UpdatableAttr<>(Menu.ShowColumn.ICON.getCode(), existingMenu.getIcon(), menu.getIcon()));
        }
        if (UpdatableVO.modified(existingMenu.getStatus(), menu.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Menu.ShowColumn.STATUS.getCode(), existingMenu.getStatus(), menu.getStatus()));
        }
    }
}
