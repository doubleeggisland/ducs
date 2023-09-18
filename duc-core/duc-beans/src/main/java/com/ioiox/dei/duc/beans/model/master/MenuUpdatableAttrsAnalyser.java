package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;

public class MenuUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<MenuMasterStdVO, MenuSlaveVO, MenuUpdatableObj, MenuUpdateCtx> {

    @Override
    public MenuUpdateCtx analyseUpdatedAttrs(final MenuMasterStdVO menu, final MenuSlaveVO existingMenu) {
        final MenuUpdateCtx updateCtx = new MenuUpdateCtx();
        updateCtx.setUpdatableObj(new MenuUpdatableObj());
        analyseUpdatedAttrs(menu, existingMenu, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final MenuMasterStdVO menu, final MenuSlaveVO existingMenu, final MenuUpdateCtx updateCtx) {
        if (UpdatableObj.modified(existingMenu.getCode(), menu.getCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.CODE.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Menu.ShowColumn.CODE.getCode(), existingMenu.getCode(), menu.getCode()));
        }
        if (UpdatableObj.modified(existingMenu.getName(), menu.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Menu.ShowColumn.NAME.getCode(), existingMenu.getName(), menu.getName()));
        }
        if (UpdatableObj.modified(existingMenu.getPid(), menu.getPid())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.PARENT_SID.getCode());
            updateCtx.getUpdatableObj().setPid(new UpdatableAttr<>(Menu.ShowColumn.PARENT_SID.getCode(), existingMenu.getPid(), menu.getPid()));
        }
        if (UpdatableObj.modified(existingMenu.getLvl(), menu.getLvl())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.LEVEL.getCode());
            updateCtx.getUpdatableObj().setLvl(new UpdatableAttr<>(Menu.ShowColumn.LEVEL.getCode(), existingMenu.getLvl(), menu.getLvl()));
        }
        if (UpdatableObj.modified(existingMenu.getRoutePath(), menu.getRoutePath())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.ROUTE_PATH.getCode());
            updateCtx.getUpdatableObj().setRoutePath(new UpdatableAttr<>(Menu.ShowColumn.ROUTE_PATH.getCode(), existingMenu.getRoutePath(), menu.getRoutePath()));
        }
        if (UpdatableObj.modified(existingMenu.getComponentUrl(), menu.getComponentUrl())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.COMPONENT_URL.getCode());
            updateCtx.getUpdatableObj().setComponentUrl(new UpdatableAttr<>(Menu.ShowColumn.COMPONENT_URL.getCode(), existingMenu.getComponentUrl(), menu.getComponentUrl()));
        }
        if (UpdatableObj.modified(existingMenu.getRedirectPath(), menu.getRedirectPath())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.REDIRECT_PATH.getCode());
            updateCtx.getUpdatableObj().setRedirectPath(new UpdatableAttr<>(Menu.ShowColumn.REDIRECT_PATH.getCode(), existingMenu.getRedirectPath(), menu.getRedirectPath()));
        }
        if (UpdatableObj.modified(existingMenu.getIsHidden(), menu.getIsHidden())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.IS_HIDDEN.getCode());
            updateCtx.getUpdatableObj().setIsHidden(new UpdatableAttr<>(Menu.ShowColumn.IS_HIDDEN.getCode(), existingMenu.getIsHidden(), menu.getIsHidden()));
        }
        if (UpdatableObj.modified(existingMenu.getIsCache(), menu.getIsCache())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.IS_CACHE.getCode());
            updateCtx.getUpdatableObj().setIsCache(new UpdatableAttr<>(Menu.ShowColumn.IS_CACHE.getCode(), existingMenu.getIsCache(), menu.getIsCache()));
        }
        if (UpdatableObj.modified(existingMenu.getIcon(), menu.getIcon())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.ICON.getCode());
            updateCtx.getUpdatableObj().setIcon(new UpdatableAttr<>(Menu.ShowColumn.ICON.getCode(), existingMenu.getIcon(), menu.getIcon()));
        }
        if (UpdatableObj.modified(existingMenu.getStatus(), menu.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Menu.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Menu.ShowColumn.STATUS.getCode(), existingMenu.getStatus(), menu.getStatus()));
        }
    }
}
