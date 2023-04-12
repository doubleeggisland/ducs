package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.beans.vo.std.master.MenuSysApiMappingMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;

public class MenuSysApiMappingUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<MenuSysApiMappingMasterStdVO, MenuSysApiMappingSlaveStdVO, MenuSysApiMappingUpdatableObj, MenuSysApiMappingUpdateCtx> {

    @Override
    public MenuSysApiMappingUpdateCtx analyseUpdatedAttrs(final MenuSysApiMappingMasterStdVO apiMapping,
                                                          final MenuSysApiMappingSlaveStdVO existingApiMapping) {
        final MenuSysApiMappingUpdateCtx updateCtx = new MenuSysApiMappingUpdateCtx();
        updateCtx.setUpdatableObj(new MenuSysApiMappingUpdatableObj());
        analyseUpdatedAttrs(apiMapping, existingApiMapping, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final MenuSysApiMappingMasterStdVO apiMapping,
                                       final MenuSysApiMappingSlaveStdVO existingApiMapping,
                                       final MenuSysApiMappingUpdateCtx updateCtx) {
        if (UpdatableVO.modified(existingApiMapping.getInteractForm(), apiMapping.getInteractForm())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(MenuSysApiMapping.ShowColumn.INTERACT_FORM.getCode());
            updateCtx.getUpdatableObj().setInteractForm(new UpdatableAttr<>(MenuSysApiMapping.ShowColumn.INTERACT_FORM.getCode(), existingApiMapping.getInteractForm(), apiMapping.getInteractForm()));
        }
    }
}
