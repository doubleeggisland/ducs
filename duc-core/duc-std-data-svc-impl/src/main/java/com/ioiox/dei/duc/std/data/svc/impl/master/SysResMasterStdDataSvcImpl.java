package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.beans.model.master.SysResUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.SysResUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.SysResUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.SysResDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;
import com.ioiox.dei.duc.db.service.master.SysResMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.SysResMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysResSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Service("sysResMasterStdDataSvc")
public class SysResMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<SysResMasterVO, SysResUpdatableObj, SysRes>
        implements SysResMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(SysResMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("sysResMasterDbSvc")
    private SysResMasterDbSvc sysResMasterDbSvc;

    @Autowired
    @Qualifier("sysResSlaveStdDataSvc")
    private SysResSlaveStdDataSvc sysResSlaveStdDataSvc;

    private final SysResUpdatableAttrsAnalyser analyser = new SysResUpdatableAttrsAnalyser();

    @Override
    public Long save(final SysResMasterVO sysRes) {
        if (Objects.isNull(sysRes)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final SysRes newEntity = toNewEntity(sysRes);
        newEntity.setDefaultValueIfNeed();
        sysResMasterDbSvc.dbInsert(newEntity);
        return newEntity.getSid();
    }

    private SysResSlaveVO getExistingSysRes(final Long id) {
        return sysResSlaveStdDataSvc.queryByPk(id,
                new DefaultStdDataQueryCfg.Builder()
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                SysRes.ShowColumn.CODE.getCode(), SysRes.ShowColumn.NAME.getCode(),
                                SysRes.ShowColumn.TYPE.getCode(), SysRes.ShowColumn.STATUS.getCode(),
                                SysRes.ShowColumn.MEMO.getCode(), SysRes.ShowColumn.SYS_PRJ_MODULE_NAME.getCode(),
                                SysRes.ShowColumn.SYS_PRJ_MODULE_CODE.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    public boolean update(final SysResMasterVO sysRes) {
        if (Objects.isNull(sysRes)
                || Objects.isNull(sysRes.getId())) {
            throw new DeiServiceException("Please choose a sysRes to update");
        }
        final SysResSlaveVO existingSysRes = getExistingSysRes(sysRes.getId());
        if (Objects.isNull(existingSysRes)) {
            throw new DeiServiceException(String.format("SysRes doesn't exist =====> id: %s", sysRes.getId()));
        }
        return update(sysRes, existingSysRes);
    }

    private boolean update(final SysResMasterVO sysRes,
                           final SysResSlaveVO existingSysRes) {
        final SysResUpdateCtx updateCtx = analyser.analyseUpdatedAttrs(sysRes, existingSysRes);
        final SysResUpdatableObj updatableObj = updateCtx.getUpdatableObj();
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                final Map<String, String> updateSummary = updatableObj.updateSummary();
                log.info(String.format("update SysRes =====> id: %s, updateSummary: %s", existingSysRes.getId(), JsonUtil.toJsonStr(updateSummary)));
            }
            final SysRes example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingSysRes, sysRes);
            final int updatedRows = sysResMasterDbSvc.dbUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("SysRes has been updated by others =====> id: %s, versionNum: %s",
                        existingSysRes.getId(), existingSysRes.getVersionNum()));
            }
        }
        return updated;
    }

    @Override
    public int remove(final SysResDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        return sysResMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public SysRes toNewEntity(final SysResMasterVO sysRes) {
        final SysRes newEntity = new SysRes();
        assembleCommonAttrsOnInsert(newEntity, sysRes);
        newEntity.setCode(sysRes.getCode());
        newEntity.setName(sysRes.getName());
        newEntity.setType(sysRes.getType());
        newEntity.setStatus(sysRes.getStatus());
        newEntity.setMemo(sysRes.getMemo());
        newEntity.setSysPrjModuleName(sysRes.getSysPrjModuleName());
        newEntity.setSysPrjModuleCode(sysRes.getSysPrjModuleCode());
        newEntity.setSysPrjSid(sysRes.getSysPrjId());
        return newEntity;
    }

    @Override
    public SysRes toUpdatableObj(final SysResUpdatableObj updatableVO) {
        final SysRes example = new SysRes();
        assembleCommonUpdatableAttrs(example, updatableVO);
        if (Objects.nonNull(updatableVO.getCode())) {
            example.setCode(updatableVO.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getName())) {
            example.setName(updatableVO.getName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getType())) {
            example.setType(updatableVO.getType().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getStatus())) {
            example.setStatus(updatableVO.getStatus().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getMemo())) {
            example.setMemo(updatableVO.getMemo().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getSysPrjModuleName())) {
            example.setSysPrjModuleName(updatableVO.getSysPrjModuleName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getSysPrjModuleCode())) {
            example.setSysPrjModuleCode(updatableVO.getSysPrjModuleCode().getNewVal());
        }
        return example;
    }
}
