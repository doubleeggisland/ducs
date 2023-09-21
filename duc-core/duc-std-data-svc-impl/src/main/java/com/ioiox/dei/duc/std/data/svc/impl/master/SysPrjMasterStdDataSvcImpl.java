package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SysPrj;
import com.ioiox.dei.duc.beans.model.master.SysPrjUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.SysPrjUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.SysPrjUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.SysPrjDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterVO;
import com.ioiox.dei.duc.beans.model.slave.SysPrjQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysPrjSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.SysPrjMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.SysPrjMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysPrjSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Service("sysPrjMasterStdDataSvc")
public class SysPrjMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<SysPrjMasterVO, SysPrjUpdatableObj, SysPrj>
        implements SysPrjMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(SysPrjMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("sysPrjMasterDbSvc")
    private SysPrjMasterDbSvc sysPrjMasterDbSvc;

    @Autowired
    @Qualifier("sysPrjSlaveStdDataSvc")
    private SysPrjSlaveStdDataSvc sysPrjSlaveStdDataSvc;

    private final SysPrjUpdatableAttrsAnalyser analyser = new SysPrjUpdatableAttrsAnalyser();

    @Override
    public Long save(final SysPrjMasterVO sysPrj) {
        if (Objects.isNull(sysPrj)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final SysPrj newEntity = toNewEntity(sysPrj);
        newEntity.setDefaultValueIfNeed();
        sysPrjMasterDbSvc.dbInsert(newEntity);
        return newEntity.getSid();
    }

    private SysPrjSlaveStdVO getExistingSysPrj(final Long id) {
        return sysPrjSlaveStdDataSvc.queryByPk(id,
                new SysPrjQueryCfg.Builder()
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                SysPrj.ShowColumn.CODE.getCode(), SysPrj.ShowColumn.NAME.getCode(),
                                SysPrj.ShowColumn.STATUS.getCode(), SysPrj.ShowColumn.MEMO.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    public boolean update(final SysPrjMasterVO sysPrj) {
        if (Objects.isNull(sysPrj)
                || Objects.isNull(sysPrj.getId())) {
            throw new DeiServiceException("Please choose a sysPrj to update");
        }
        final SysPrjSlaveStdVO existingSysPrj = getExistingSysPrj(sysPrj.getId());
        if (Objects.isNull(existingSysPrj)) {
            throw new DeiServiceException(String.format("SysPrj doesn't exist =====> id: %s", sysPrj.getId()));
        }
        return update(sysPrj, existingSysPrj);
    }

    private boolean update(final SysPrjMasterVO sysPrj,
                           final SysPrjSlaveStdVO existingSysPrj) {
        final SysPrjUpdateCtx updateCtx = analyser.analyseUpdatedAttrs(sysPrj, existingSysPrj);
        final SysPrjUpdatableObj updatableObj = updateCtx.getUpdatableObj();
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                final Map<String, String> updateSummary = updatableObj.updateSummary();
                log.info(String.format("update SysPrj =====> id: %s, updateSummary: %s", existingSysPrj.getId(), JsonUtil.toJsonStr(updateSummary)));
            }
            final SysPrj example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingSysPrj, sysPrj);
            final int updatedRows = sysPrjMasterDbSvc.dbUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("SysPrj has been updated by others =====> id: %s, versionNum: %s",
                        existingSysPrj.getId(), existingSysPrj.getVersionNum()));
            }
        }
        return updated;
    }

    @Override
    public int remove(final SysPrjDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        return sysPrjMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public SysPrj toNewEntity(final SysPrjMasterVO sysPrj) {
        final SysPrj newEntity = new SysPrj();
        assembleCommonAttrsOnInsert(newEntity, sysPrj);
        newEntity.setCode(sysPrj.getCode());
        newEntity.setName(sysPrj.getName());
        newEntity.setStatus(sysPrj.getStatus());
        newEntity.setMemo(sysPrj.getMemo());
        return newEntity;
    }

    @Override
    public SysPrj toUpdatableObj(final SysPrjUpdatableObj updatableVO) {
        final SysPrj example = new SysPrj();
        assembleCommonUpdatableAttrs(example, updatableVO);
        if (Objects.nonNull(updatableVO.getCode())) {
            example.setCode(updatableVO.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getName())) {
            example.setName(updatableVO.getName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getStatus())) {
            example.setStatus(updatableVO.getStatus().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getMemo())) {
            example.setMemo(updatableVO.getMemo().getNewVal());
        }
        return example;
    }
}
