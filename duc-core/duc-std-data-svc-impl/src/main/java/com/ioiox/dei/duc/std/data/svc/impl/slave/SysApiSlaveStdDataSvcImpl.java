package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.beans.model.slave.SysApiQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.SysApiSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysApiSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("sysApiSlaveStdDataSvc")
public class SysApiSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<SysApiSlaveStdVO, SysApi>
        implements SysApiSlaveStdDataSvc {

    @Autowired
    @Qualifier("sysApiSlaveDbSvc")
    private SysApiSlaveDbSvc sysApiSlaveDbSvc;

    @Override
    public int countByParam(final SysApiQueryParam queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return sysApiSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    public List<SysApiSlaveStdVO> queryBySysPrjIds(final List<Long> sysPrjIds,
                                                   final StdDataQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysPrjIds)) {
            return Collections.emptyList();
        }
        final SysApiQueryParam queryParam = new SysApiQueryParam.Builder()
                .sysPrjIds(sysPrjIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public SysApiSlaveStdVO queryByPk(final Long sysApiId, final StdDataQueryCfg queryCfg) {
        final List<SysApiSlaveStdVO> sysApis = queryByPks(Collections.singletonList(sysApiId), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysApis)) {
            return null;
        }
        return sysApis.get(0);
    }

    @Override
    public List<SysApiSlaveStdVO> queryByPks(final List<Long> sysApiIds, final StdDataQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysApiIds)) {
            return Collections.emptyList();
        }
        final SysApiQueryParam queryParam = new SysApiQueryParam.Builder()
                .pks(sysApiIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<SysApiSlaveStdVO> queryByParam(final SysApiQueryParam queryParam,
                                               final StdDataQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<SysApi> entities =
                sysApiSlaveDbSvc.findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream().map(this::transferToStdDataVO)
                .collect(Collectors.toList());
    }

    @Override
    public SysApiSlaveStdVO transferToStdDataVO(final SysApi entity) {
        final SysApiSlaveStdVO stdVO = new SysApiSlaveStdVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setCode(entity.getCode());
        stdVO.setName(entity.getName());
        stdVO.setType(entity.getType());
        stdVO.setMemo(entity.getMemo());
        stdVO.setUrl(entity.getUrl());
        stdVO.setHttpMethod(entity.getHttpMethod());
        stdVO.setSysPrjModuleCode(entity.getSysPrjModuleCode());
        stdVO.setSysPrjModuleName(entity.getSysPrjModuleName());
        stdVO.setSysPrjId(entity.getSysPrjSid());
        stdVO.setStatus(entity.getStatus());
        return stdVO;
    }
}
