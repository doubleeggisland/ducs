package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.beans.model.master.SysApiUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.SysApiUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.SysApiUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.SysApiDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.db.service.master.SysApiMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.SysApiMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysApiSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Service("sysApiMasterStdDataSvc")
public class SysApiMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<SysApiMasterVO, SysApiUpdatableObj, SysApi>
        implements SysApiMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(SysApiMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("sysApiMasterDbSvc")
    private SysApiMasterDbSvc sysApiMasterDbSvc;

    @Autowired
    @Qualifier("sysApiSlaveStdDataSvc")
    private SysApiSlaveStdDataSvc sysApiSlaveStdDataSvc;

    private final SysApiUpdatableAttrsAnalyser analyser = new SysApiUpdatableAttrsAnalyser();

    @Override
    public Long save(final SysApiMasterVO sysApi) {
        if (Objects.isNull(sysApi)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final SysApi newEntity = toNewEntity(sysApi);
        newEntity.setDefaultValueIfNeed();
        sysApiMasterDbSvc.dbInsert(newEntity);
        return newEntity.getSid();
    }

    private SysApiSlaveVO getExistingSysApi(final Long id) {
        return sysApiSlaveStdDataSvc.queryByPk(id,
                new DefaultStdDataQueryCfg.Builder()
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                SysApi.ShowColumn.CODE.getCode(), SysApi.ShowColumn.NAME.getCode(),
                                SysApi.ShowColumn.TYPE.getCode(), SysApi.ShowColumn.MEMO.getCode(),
                                SysApi.ShowColumn.URL.getCode(), SysApi.ShowColumn.HTTP_METHOD.getCode(),
                                SysApi.ShowColumn.SYS_PRJ_MODULE_NAME.getCode(), SysApi.ShowColumn.SYS_PRJ_MODULE_CODE.getCode(),
                                SysApi.ShowColumn.STATUS.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    public boolean update(final SysApiMasterVO sysApi) {
        if (Objects.isNull(sysApi)
                || Objects.isNull(sysApi.getId())) {
            throw new DeiServiceException("Please choose a sysApi to update");
        }
        final SysApiSlaveVO existingSysApi = getExistingSysApi(sysApi.getId());
        if (Objects.isNull(existingSysApi)) {
            throw new DeiServiceException(String.format("SysApi doesn't exist =====> id: %s", sysApi.getId()));
        }
        return update(sysApi, existingSysApi);
    }

    private boolean update(final SysApiMasterVO sysApi,
                           final SysApiSlaveVO existingSysApi) {
        final SysApiUpdateCtx updateCtx = analyser.analyseUpdatedAttrs(sysApi, existingSysApi);
        final SysApiUpdatableObj updatableObj = updateCtx.getUpdatableObj();
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                final Map<String, String> updateSummary = updatableObj.updateSummary();
                log.info(String.format("update SysApi =====> id: %s, updateSummary: %s", existingSysApi.getId(), JsonUtil.toJsonStr(updateSummary)));
            }
            final SysApi example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingSysApi, sysApi);
            final int updatedRows = sysApiMasterDbSvc.dbUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("SysApi has been updated by others =====> id: %s, versionNum: %s",
                        existingSysApi.getId(), existingSysApi.getVersionNum()));
            }
        }
        return updated;
    }

    @Override
    public int remove(final SysApiDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        return sysApiMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public SysApi toNewEntity(final SysApiMasterVO sysApi) {
        final SysApi newEntity = new SysApi();
        assembleCommonAttrsOnInsert(newEntity, sysApi);
        newEntity.setCode(sysApi.getCode());
        newEntity.setName(sysApi.getName());
        newEntity.setType(sysApi.getType());
        newEntity.setMemo(sysApi.getMemo());
        newEntity.setUrl(sysApi.getUrl());
        newEntity.setHttpMethod(sysApi.getHttpMethod());
        newEntity.setSysPrjModuleName(sysApi.getSysPrjModuleName());
        newEntity.setSysPrjModuleCode(sysApi.getSysPrjModuleCode());
        newEntity.setSysPrjSid(sysApi.getSysPrjId());
        newEntity.setStatus(sysApi.getStatus());
        return newEntity;
    }

    @Override
    public SysApi toUpdatableObj(final SysApiUpdatableObj updatableVO) {
        final SysApi example = new SysApi();
        assembleCommonAttrs(example, updatableVO);
        if (Objects.nonNull(updatableVO.getCode())) {
            example.setCode(updatableVO.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getName())) {
            example.setName(updatableVO.getName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getType())) {
            example.setType(updatableVO.getType().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getMemo())) {
            example.setMemo(updatableVO.getMemo().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getUrl())) {
            example.setUrl(updatableVO.getUrl().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getHttpMethod())) {
            example.setHttpMethod(updatableVO.getHttpMethod().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getSysPrjModuleName())) {
            example.setSysPrjModuleName(updatableVO.getSysPrjModuleName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getSysPrjModuleCode())) {
            example.setSysPrjModuleCode(updatableVO.getSysPrjModuleCode().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getStatus())) {
            example.setStatus(updatableVO.getStatus().getNewVal());
        }
        return example;
    }
}
