package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;

public class SysApiUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<SysApiMasterVO, SysApiSlaveVO, SysApiUpdatableObj, SysApiUpdateCtx> {

    @Override
    public SysApiUpdateCtx analyseUpdatedAttrs(final SysApiMasterVO sysApi, final SysApiSlaveVO existingSysApi) {
        final SysApiUpdateCtx updateCtx = new SysApiUpdateCtx();
        updateCtx.setUpdatableObj(new SysApiUpdatableObj());
        analyseUpdatedAttrs(sysApi, existingSysApi, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final SysApiMasterVO sysApi, final SysApiSlaveVO existingSysApi, final SysApiUpdateCtx updateCtx) {
        if (UpdatableObj.modified(existingSysApi.getCode(), sysApi.getCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.CODE.getCode());
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(SysApi.ShowColumn.CODE.getCode(), existingSysApi.getCode(), sysApi.getCode()));
        }
        if (UpdatableObj.modified(existingSysApi.getName(), sysApi.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(SysApi.ShowColumn.NAME.getCode(), existingSysApi.getName(), sysApi.getName()));
        }
        if (UpdatableObj.modified(existingSysApi.getType(), sysApi.getType())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.TYPE.getCode());
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(SysApi.ShowColumn.TYPE.getCode(), existingSysApi.getType(), sysApi.getType()));
        }
        if (UpdatableObj.modified(existingSysApi.getMemo(), sysApi.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(SysApi.ShowColumn.MEMO.getCode(), existingSysApi.getMemo(), sysApi.getMemo()));
        }
        if (UpdatableObj.modified(existingSysApi.getUrl(), sysApi.getUrl())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.URL.getCode());
            updateCtx.getUpdatableObj().setUrl(new UpdatableAttr<>(SysApi.ShowColumn.URL.getCode(), existingSysApi.getUrl(), sysApi.getUrl()));
        }
        if (UpdatableObj.modified(existingSysApi.getHttpMethod(), sysApi.getHttpMethod())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.HTTP_METHOD.getCode());
            updateCtx.getUpdatableObj().setHttpMethod(new UpdatableAttr<>(SysApi.ShowColumn.HTTP_METHOD.getCode(), existingSysApi.getHttpMethod(), sysApi.getHttpMethod()));
        }
        if (UpdatableObj.modified(existingSysApi.getSysPrjModuleName(), sysApi.getSysPrjModuleName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.SYS_PRJ_MODULE_NAME.getCode());
            updateCtx.getUpdatableObj().setSysPrjModuleName(new UpdatableAttr<>(SysApi.ShowColumn.SYS_PRJ_MODULE_NAME.getCode(), existingSysApi.getSysPrjModuleName(), sysApi.getSysPrjModuleName()));
        }
        if (UpdatableObj.modified(existingSysApi.getSysPrjModuleCode(), sysApi.getSysPrjModuleCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.SYS_PRJ_MODULE_CODE.getCode());
            updateCtx.getUpdatableObj().setSysPrjModuleCode(new UpdatableAttr<>(SysApi.ShowColumn.SYS_PRJ_MODULE_CODE.getCode(), existingSysApi.getSysPrjModuleCode(), sysApi.getSysPrjModuleCode()));
        }
        if (UpdatableObj.modified(existingSysApi.getStatus(), sysApi.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysApi.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(SysApi.ShowColumn.STATUS.getCode(), existingSysApi.getStatus(), sysApi.getStatus()));
        }
    }
}
