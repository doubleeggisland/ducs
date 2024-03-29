package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.beans.model.slave.SysResQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;
import com.ioiox.dei.duc.db.service.slave.SysResSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysResSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("sysResSlaveStdDataSvc")
public class SysResSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<SysResSlaveVO, SysRes>
        implements SysResSlaveStdDataSvc {

    @Autowired
    @Qualifier("sysResSlaveDbSvc")
    private SysResSlaveDbSvc sysResSlaveDbSvc;

    @Override
    public int countByParam(final SysResQueryParam queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return sysResSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    public List<SysResSlaveVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                                final StdDataQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysPrjIds)) {
            return Collections.emptyList();
        }
        final SysResQueryParam queryParam = new SysResQueryParam.Builder()
                .sysPrjIds(sysPrjIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public SysResSlaveVO queryByPk(final Long sysResId,
                                   final StdDataQueryCfg queryCfg) {
        final List<SysResSlaveVO> sysResources = queryByPks(Collections.singletonList(sysResId), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResources)) {
            return null;
        }
        return sysResources.get(0);
    }

    @Override
    public List<SysResSlaveVO> queryByPks(final List<Long> sysResIds,
                                          final StdDataQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysResIds)) {
            return Collections.emptyList();
        }
        final SysResQueryParam queryParam = new SysResQueryParam.Builder()
                .pks(sysResIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<SysResSlaveVO> queryByParam(final SysResQueryParam queryParam,
                                            final StdDataQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<SysRes> entities = sysResSlaveDbSvc.findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::transferToStdDataVO)
                .collect(Collectors.toList());
    }

    @Override
    public SysResSlaveVO transferToStdDataVO(final SysRes entity) {
        final SysResSlaveVO stdVO = new SysResSlaveVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setCode(entity.getCode());
        stdVO.setName(entity.getName());
        stdVO.setType(entity.getType());
        stdVO.setStatus(entity.getStatus());
        stdVO.setMemo(entity.getMemo());
        stdVO.setSysPrjModuleCode(entity.getSysPrjModuleCode());
        stdVO.setSysPrjModuleName(entity.getSysPrjModuleName());
        stdVO.setSysPrjId(entity.getSysPrjSid());
        return stdVO;
    }
}
