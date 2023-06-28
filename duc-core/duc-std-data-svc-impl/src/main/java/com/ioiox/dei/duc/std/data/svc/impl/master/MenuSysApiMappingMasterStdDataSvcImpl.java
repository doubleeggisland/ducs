package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.ChildrenAnalyser;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.beans.model.master.MenuSysApiMappingUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.MenuSysApiMappingUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.MenuSysApiMappingUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.MenuSysApiMappingDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuSysApiMappingMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.MenuSysApiMappingMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.MenuSysApiMappingMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSysApiMappingSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("menuSysApiMappingMasterStdDataSvc")
public class MenuSysApiMappingMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<MenuSysApiMappingMasterStdVO, MenuSysApiMappingUpdatableObj, MenuSysApiMapping>
        implements MenuSysApiMappingMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(MenuSysApiMappingMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("menuSysApiMappingMasterDbSvc")
    private MenuSysApiMappingMasterDbSvc menuSysApiMappingMasterDbSvc;

    @Autowired
    @Qualifier("menuSysApiMappingSlaveStdDataSvc")
    private MenuSysApiMappingSlaveStdDataSvc menuSysApiMappingSlaveStdDataSvc;

    private final MenuSysApiMappingUpdatableAttrsAnalyser analyser = new MenuSysApiMappingUpdatableAttrsAnalyser();

    @Override
    public int sync(final List<MenuSysApiMappingMasterStdVO> sysApiMappings,
                    final List<MenuSysApiMappingSlaveStdVO> existingSysApiMappings) {
        final ChildrenAnalyser.DiffHolder<MenuSysApiMappingMasterStdVO, MenuSysApiMappingSlaveStdVO> diff =
                ChildrenAnalyser.analysisDiff(sysApiMappings, existingSysApiMappings);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewChildren())) {
            insertedRows = save(diff.getNewChildren());
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int totalUpdatedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getUpdatableChildren())) {
            final List<ChildrenAnalyser.ChildHolder<MenuSysApiMappingMasterStdVO, MenuSysApiMappingSlaveStdVO>> updatedChildren = new LinkedList<>();
            for (final ChildrenAnalyser.ChildHolder<MenuSysApiMappingMasterStdVO, MenuSysApiMappingSlaveStdVO> childHolder : diff.getUpdatableChildren()) {
                final boolean updated = update(childHolder.getChild(), childHolder.getExistingChild());
                if (updated) {
                    updatedChildren.add(childHolder);
                }
            }
            totalUpdatedRows = updatedChildren.size();
        } else {
            totalUpdatedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedChildren())) {
            deletedRows = remove(diff.getRemovedChildren().stream()
                    .map(MenuSysApiMappingSlaveStdVO::getId)
                    .collect(Collectors.toList()));
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }

        if (log.isInfoEnabled()) {
            log.info(String.format("sync MenuSysApiMappings =====> insertedRows: %s, totalUpdatedRows: %s, deletedRows: %s",
                    insertedRows, totalUpdatedRows, deletedRows));
        }
        return insertedRows + totalUpdatedRows + deletedRows;
    }

    @Override
    public Long save(final MenuSysApiMappingMasterStdVO sysApiMapping) {
        if (Objects.isNull(sysApiMapping)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("save MenuSysApiMapping =====> %s", JsonUtil.toJsonStr(sysApiMapping)));
        }
        final MenuSysApiMapping newEntity = toNewEntity(sysApiMapping);
        newEntity.setDefaultValueIfNeed();
        menuSysApiMappingMasterDbSvc.dbInsert(newEntity);
        return newEntity.getSid();
    }

    public int save(final List<MenuSysApiMappingMasterStdVO> sysApiMappings) {
        if (log.isInfoEnabled()) {
            log.info(String.format("save MenuSysApiMappings =====> %s", JsonUtil.toJsonStr(sysApiMappings)));
        }
        final List<MenuSysApiMapping> newEntities =
                sysApiMappings.stream().map(item -> {
                            final MenuSysApiMapping entity = toNewEntity(item);
                            entity.setDefaultValueIfNeed();
                            return entity;
                        })
                        .collect(Collectors.toList());
        return menuSysApiMappingMasterDbSvc.dbInsert(newEntities);
    }

    public boolean update(final MenuSysApiMappingMasterStdVO sysApiMapping,
                          final MenuSysApiMappingSlaveStdVO existingSysApiMapping) {
        final MenuSysApiMappingUpdateCtx updateCtx = analyser.analyseUpdatedAttrs(sysApiMapping, existingSysApiMapping);
        final MenuSysApiMappingUpdatableObj updatableObj = updateCtx.getUpdatableObj();

        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                final Map<String, String> updateSummary = updatableObj.updateSummary();
                log.info(String.format("update MenuSysApiMapping =====> id: %s, updateSummary: %s", existingSysApiMapping.getId(), JsonUtil.toJsonStr(updateSummary)));
            }
            final MenuSysApiMapping example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingSysApiMapping, sysApiMapping);
            final int updatedRows = menuSysApiMappingMasterDbSvc.dbUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("MenuSysApiMapping has been updated by others =====> id: %s, versionNum: %s",
                        existingSysApiMapping.getId(), existingSysApiMapping.getVersionNum()));
            }
        }
        return updated;
    }

    @Override
    public int remove(final MenuSysApiMappingDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        return menuSysApiMappingMasterDbSvc.deleteByParams(deleteParams);
    }

    public int remove(final List<Long> pks) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("remove MenuSysApiMappings =====> ids: %s", JsonUtil.toJsonStr(pks)));
        }
        final MenuSysApiMappingDelParam delParam = new MenuSysApiMappingDelParam.Builder()
                .pks(pks)
                .build();
        return remove(delParam);
    }

    @Override
    public MenuSysApiMapping toNewEntity(final MenuSysApiMappingMasterStdVO masterStdVO) {
        final MenuSysApiMapping newEntity = new MenuSysApiMapping();
        assembleCommonAttrsOnInsert(newEntity, masterStdVO);
        newEntity.setMenuSid(masterStdVO.getMenuId());
        newEntity.setSysApiSid(masterStdVO.getSysApiId());
        newEntity.setInteractForm(masterStdVO.getInteractForm());
        return newEntity;
    }

    @Override
    public MenuSysApiMapping toUpdatableObj(final MenuSysApiMappingUpdatableObj updatableVO) {
        final MenuSysApiMapping example = new MenuSysApiMapping();
        if (Objects.nonNull(updatableVO.getInteractForm())) {
            example.setInteractForm(updatableVO.getInteractForm().getNewVal());
        }
        return example;
    }
}
