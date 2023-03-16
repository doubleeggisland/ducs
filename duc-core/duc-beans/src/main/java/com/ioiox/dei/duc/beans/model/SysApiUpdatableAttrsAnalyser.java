package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveStdVO;

public class SysApiUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<SysApiMasterStdVO, SysApiSlaveStdVO, SysApiUpdatableObj, SysApiUpdateCtx> {

    @Override
    public SysApiUpdateCtx analyseUpdatedAttrs(final SysApiMasterStdVO sysApi, final SysApiSlaveStdVO existingSysApi) {
        final SysApiUpdateCtx updateCtx = new SysApiUpdateCtx();
        updateCtx.setUpdatableObj(new SysApiUpdatableObj());
        analyseUpdatedAttrs(sysApi, existingSysApi, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final SysApiMasterStdVO sysApi, final SysApiSlaveStdVO existingSysApi, final SysApiUpdateCtx updateCtx) {
        if (UpdatableVO.modified(existingSysApi.getCode(), sysApi.getCode())) {
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(SysApi.ShowColumn.CODE.getCode(), existingSysApi.getCode(), sysApi.getCode()));
        }
        if (UpdatableVO.modified(existingSysApi.getName(), sysApi.getName())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(SysApi.ShowColumn.NAME.getCode(), existingSysApi.getName(), sysApi.getName()));
        }
        if (UpdatableVO.modified(existingSysApi.getType(), sysApi.getType())) {
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(SysApi.ShowColumn.TYPE.getCode(), existingSysApi.getType(), sysApi.getType()));
        }
        if (UpdatableVO.modified(existingSysApi.getUrl(), sysApi.getUrl())) {
            updateCtx.getUpdatableObj().setUrl(new UpdatableAttr<>(SysApi.ShowColumn.URL.getCode(), existingSysApi.getUrl(), sysApi.getUrl()));
        }
        if (UpdatableVO.modified(existingSysApi.getHttpMethod(), sysApi.getHttpMethod())) {
            updateCtx.getUpdatableObj().setHttpMethod(new UpdatableAttr<>(SysApi.ShowColumn.HTTP_METHOD.getCode(), existingSysApi.getHttpMethod(), sysApi.getHttpMethod()));
        }
        if (UpdatableVO.modified(existingSysApi.getSysPrjModuleName(), sysApi.getSysPrjModuleName())) {
            updateCtx.getUpdatableObj().setSysPrjModuleName(new UpdatableAttr<>(SysApi.ShowColumn.SYS_PRJ_MODULE_NAME.getCode(), existingSysApi.getSysPrjModuleName(), sysApi.getSysPrjModuleName()));
        }
        if (UpdatableVO.modified(existingSysApi.getSysPrjModuleCode(), sysApi.getSysPrjModuleCode())) {
            updateCtx.getUpdatableObj().setSysPrjModuleCode(new UpdatableAttr<>(SysApi.ShowColumn.SYS_PRJ_MODULE_CODE.getCode(), existingSysApi.getSysPrjModuleCode(), sysApi.getSysPrjModuleCode()));
        }
        if (UpdatableVO.modified(existingSysApi.getStatus(), sysApi.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(SysApi.ShowColumn.STATUS.getCode(), existingSysApi.getStatus(), sysApi.getStatus()));
        }
    }
}
