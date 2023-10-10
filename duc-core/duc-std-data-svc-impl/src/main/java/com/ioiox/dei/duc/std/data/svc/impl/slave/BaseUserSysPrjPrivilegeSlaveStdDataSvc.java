package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.model.slave.SysPrjQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserSysPrjPrivilegeQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserSysPrjPrivilegeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.std.data.svc.slave.SysPrjSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.UserSysPrjPrivilegeSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseUserSysPrjPrivilegeSlaveStdDataSvc
        extends BaseDeiSlaveStdDataSvc<UserSysPrjPrivilegeSlaveVO, UserSysPrjPrivilege>
        implements UserSysPrjPrivilegeSlaveStdDataSvc {

    @Autowired
    @Qualifier("sysPrjSlaveStdDataSvc")
    private SysPrjSlaveStdDataSvc sysPrjSlaveStdDataSvc;

    @Override
    public int countByParam(final UserSysPrjPrivilegeQueryParam queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return countByParams(queryParams);
    }

    @Override
    public List<UserSysPrjPrivilegeSlaveVO> queryByUserIds(final List<Long> userIds,
                                                           final UserSysPrjPrivilegeQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        final UserSysPrjPrivilegeQueryParam queryParam  = new UserSysPrjPrivilegeQueryParam.Builder()
                .userIds(userIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<UserSysPrjPrivilegeSlaveVO> queryByParam(final UserSysPrjPrivilegeQueryParam queryParam,
                                                         final UserSysPrjPrivilegeQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysPrj())) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(UserSysPrjPrivilege.ShowColumn.SYS_PRJ_SID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<UserSysPrjPrivilege> entities = findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }
        final List<Long> sysPrjIds = new ArrayList<>(entities.size());
        final List<UserSysPrjPrivilegeSlaveVO> sysPrjPrivileges = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            sysPrjIds.add(entity.getSysPrjSid());
            sysPrjPrivileges.add(transferToStdDataVO(entity));
        });

        final Map<Long, SysPrjSlaveVO> sysPrjMap;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysPrj())) {
            sysPrjMap = getSysPrjs(sysPrjIds, queryCfg.getSysPrjQueryCfg());
        } else {
            sysPrjMap = Collections.emptyMap();
        }
        sysPrjPrivileges.forEach(sysPrjPrivilege ->
                sysPrjPrivilege.setSysPrj(sysPrjMap.getOrDefault(sysPrjPrivilege.getSysPrjId(), null)));
        return sysPrjPrivileges;
    }

    protected abstract int countByParams(final Map<String, Object> queryParams);

    protected abstract List<UserSysPrjPrivilege> findByParams(final Map<String, Object> queryParams,
                                                              final List<String> showColumns);

    protected Map<Long, SysPrjSlaveVO> getSysPrjs(final List<Long> sysPrjIds,
                                                  final SysPrjQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysPrjIds)) {
            return Collections.emptyMap();
        }
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<SysPrjSlaveVO> sysPrjs =
                sysPrjSlaveStdDataSvc.queryByPks(sysPrjIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysPrjs)) {
            return Collections.emptyMap();
        }
        return sysPrjs.stream()
                .collect(Collectors.toMap(SysPrjSlaveVO::getId, sysPrj -> sysPrj));
    }

    @Override
    public UserSysPrjPrivilegeSlaveVO transferToStdDataVO(final UserSysPrjPrivilege entity) {
        final UserSysPrjPrivilegeSlaveVO stdVO = new UserSysPrjPrivilegeSlaveVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setUserId(entity.getUserSid());
        stdVO.setSysPrjId(entity.getSysPrjSid());
        stdVO.setAccessCondition(entity.getAccessCondition());
        stdVO.setSysPrjId(entity.getSysPrjSid());
        return stdVO;
    }
}
