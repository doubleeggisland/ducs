package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.model.std.data.ChildrenAnalyser;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.model.master.UserSysPrjPrivilegeUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.UserSysPrjPrivilegeUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.UserSysPrjPrivilegeUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.UserSysPrjPrivilegeDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;
import com.ioiox.dei.duc.std.data.svc.master.UserSysPrjPrivilegeMasterStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseUserSysPrjPrivilegeMasterStdDataSvc
        extends BaseDeiMasterStdDataSvc<UserSysPrjPrivilegeMasterVO, UserSysPrjPrivilegeUpdatableObj, UserSysPrjPrivilege>
        implements UserSysPrjPrivilegeMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(BaseUserSysPrjPrivilegeMasterStdDataSvc.class);

    private final UserSysPrjPrivilegeUpdatableAttrsAnalyser analyser = new UserSysPrjPrivilegeUpdatableAttrsAnalyser();

    @Override
    public int sync(final List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges,
                    final List<UserSysPrjPrivilegeSlaveVO> existingSysPrjPrivileges) {
        final ChildrenAnalyser.DiffHolder<UserSysPrjPrivilegeMasterVO, UserSysPrjPrivilegeSlaveVO> diff =
                ChildrenAnalyser.analysisDiff(sysPrjPrivileges, existingSysPrjPrivileges);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewChildren())) {
            insertedRows = save(diff.getNewChildren());
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int totalUpdatedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getUpdatableChildren())) {
            final List<ChildrenAnalyser.ChildHolder<UserSysPrjPrivilegeMasterVO, UserSysPrjPrivilegeSlaveVO>> updatedChildren = new LinkedList<>();
            for (final ChildrenAnalyser.ChildHolder<UserSysPrjPrivilegeMasterVO, UserSysPrjPrivilegeSlaveVO> childHolder : diff.getUpdatableChildren()) {
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
            deletedRows = removeByPks(diff.getRemovedChildren().stream()
                    .map(UserSysPrjPrivilegeSlaveVO::getId)
                    .collect(Collectors.toList()));
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }

        if (log.isInfoEnabled()) {
            log.info(String.format("sync UserSysPrjPrivileges =====> insertedRows: %s, totalUpdatedRows: %s, deletedRows: %s",
                    insertedRows, totalUpdatedRows, deletedRows));
        }
        return insertedRows + totalUpdatedRows + deletedRows;
    }

    boolean update(final UserSysPrjPrivilegeMasterVO sysPrjPrivilege,
                   final UserSysPrjPrivilegeSlaveVO existingSysPrjPrivilege) {
        final UserSysPrjPrivilegeUpdateCtx updateCtx = analyser.analyseUpdatedAttrs(sysPrjPrivilege, existingSysPrjPrivilege);
        final UserSysPrjPrivilegeUpdatableObj updatableObj = updateCtx.getUpdatableObj();

        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                final Map<String, String> updateSummary = updatableObj.updateSummary();
                log.info(String.format("update UserSysPrjPrivilege =====> id: %s, updateSummary: %s", existingSysPrjPrivilege.getId(), JsonUtil.toJsonStr(updateSummary)));
            }
            final UserSysPrjPrivilege example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingSysPrjPrivilege, sysPrjPrivilege);
            final int updatedRows = doUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("UserSysPrjPrivilege has been updated by others =====> id: %s, versionNum: %s",
                        existingSysPrjPrivilege.getId(), existingSysPrjPrivilege.getVersionNum()));
            }
        }
        return updated;
    }

    int save(final List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges) {
        if (log.isInfoEnabled()) {
            log.info(String.format("save UserSysPrjPrivileges =====> %s", JsonUtil.toJsonStr(sysPrjPrivileges)));
        }
        final List<UserSysPrjPrivilege> newEntities =
                sysPrjPrivileges.stream().map(item -> {
                            final UserSysPrjPrivilege entity = toNewEntity(item);
                            entity.setDefaultValueIfNeed();
                            return entity;
                        })
                        .collect(Collectors.toList());
        return doSave(newEntities);
    }

    int removeByPks(final List<Long> pks) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("remove UserSysPrjPrivileges =====> ids: %s", JsonUtil.toJsonStr(pks)));
        }
        final UserSysPrjPrivilegeDelParam delParam = new UserSysPrjPrivilegeDelParam.Builder()
                .pks(pks)
                .build();
        return remove(delParam);
    }

    @Override
    public int removeByUserIds(final List<Long> userIds) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return DeiGlobalConstant.ZERO;
        }
        final UserSysPrjPrivilegeDelParam delParam = new UserSysPrjPrivilegeDelParam.Builder()
                .userIds(userIds)
                .build();
        return remove(delParam);
    }

    @Override
    public int remove(final UserSysPrjPrivilegeDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        return doRemove(deleteParams);
    }

    protected abstract int doSave(final List<UserSysPrjPrivilege> newEntities);
    protected abstract int doUpdate(final UserSysPrjPrivilege example);
    protected abstract int doRemove(final Map<String, Object> deleteParams);

    @Override
    public UserSysPrjPrivilege toNewEntity(final UserSysPrjPrivilegeMasterVO sysPrjPrivilege) {
        final UserSysPrjPrivilege newEntity = new UserSysPrjPrivilege();
        assembleCommonAttrsOnInsert(newEntity, sysPrjPrivilege);
        newEntity.setUserSid(sysPrjPrivilege.getUserId());
        newEntity.setSysPrjSid(sysPrjPrivilege.getSysPrjId());
        newEntity.setAccessCondition(sysPrjPrivilege.getAccessCondition());
        return newEntity;
    }

    @Override
    public UserSysPrjPrivilege toUpdatableObj(final UserSysPrjPrivilegeUpdatableObj updatableVO) {
        final UserSysPrjPrivilege example = new UserSysPrjPrivilege();
        assembleCommonAttrs(example, updatableVO);
        if (Objects.nonNull(updatableVO.getAccessCondition())) {
            example.setAccessCondition(updatableVO.getAccessCondition().getNewVal());
        }
        return example;
    }
}
