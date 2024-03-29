package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.Menu;
import com.ioiox.dei.duc.beans.entity.SysApi;
import com.ioiox.dei.duc.beans.entity.SysPrj;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysPrjQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysPrjQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.db.service.slave.SysPrjSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysApiSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysPrjSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysResSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("sysPrjSlaveStdDataSvc")
public class SysPrjSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<SysPrjSlaveVO, SysPrj>
        implements SysPrjSlaveStdDataSvc {

    @Autowired
    @Qualifier("sysPrjSlaveDbSvc")
    private SysPrjSlaveDbSvc sysPrjSlaveDbSvc;

    @Autowired
    @Qualifier("menuSlaveStdDataSvc")
    private MenuSlaveStdDataSvc menuSlaveStdDataSvc;

    @Autowired
    @Qualifier("sysApiSlaveStdDataSvc")
    private SysApiSlaveStdDataSvc sysApiSlaveStdDataSvc;

    @Autowired
    @Qualifier("sysResSlaveStdDataSvc")
    private SysResSlaveStdDataSvc sysResSlaveStdDataSvc;

    @Override
    public int countByParam(final SysPrjQueryParam queryParam) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? Collections.emptyMap() : queryParam.queryParams();
        return sysPrjSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    public SysPrjSlaveVO queryByPk(final Long sysPrjId,
                                   final SysPrjQueryCfg queryCfg) {
        final List<SysPrjSlaveVO> sysPrjs = queryByPks(Collections.singletonList(sysPrjId), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysPrjs)) {
            return null;
        }
        return sysPrjs.get(0);
    }

    @Override
    public List<SysPrjSlaveVO> queryByPks(final List<Long> sysPrjIds,
                                          final SysPrjQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysPrjIds)) {
            return Collections.emptyList();
        }
        final SysPrjQueryParam queryParam = new SysPrjQueryParam.Builder()
                .pks(sysPrjIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<SysPrjSlaveVO> queryByParam(final SysPrjQueryParam queryParam,
                                            final SysPrjQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();

        if (Objects.nonNull(queryCfg)
                && (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedMenus())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApis())
                    || StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResources()))) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<SysPrj> entities = sysPrjSlaveDbSvc.findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<Long> sysPrjIds = new ArrayList<>(entities.size());
        final List<SysPrjSlaveVO> sysPrjs = new ArrayList<>(entities.size());
        entities.forEach(entity -> {
            sysPrjIds.add(entity.getSid());
            sysPrjs.add(transferToStdDataVO(entity));
        });

        final Map<Long, List<MenuSlaveVO>> groupMenus;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedMenus())) {
            groupMenus = getMenus(sysPrjIds, queryCfg.getMenuQueryCfg());
        } else {
            groupMenus = Collections.emptyMap();
        }
        final Map<Long, List<SysApiSlaveVO>> groupedSysApis;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApis())) {
            groupedSysApis = getSysApis(sysPrjIds, queryCfg.getSysApiQueryCfg());
        } else {
            groupedSysApis = Collections.emptyMap();
        }
        final Map<Long, List<SysResSlaveVO>> groupedSysResources;
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysResources())) {
            groupedSysResources = getSysResources(sysPrjIds, queryCfg.getSysResQueryCfg());
        } else {
            groupedSysResources = Collections.emptyMap();
        }
        sysPrjs.forEach(sysPrj -> {
            sysPrj.setMenus(groupMenus.getOrDefault(sysPrj.getId(), Collections.emptyList()));
            sysPrj.setSysApis(groupedSysApis.getOrDefault(sysPrj.getId(), Collections.emptyList()));
            sysPrj.setSysResources(groupedSysResources.getOrDefault(sysPrj.getId(), Collections.emptyList()));
        });
        return sysPrjs;
    }

    protected Map<Long, List<MenuSlaveVO>> getMenus(final List<Long> sysPrjIds,
                                                    final MenuQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(Menu.ShowColumn.SYS_PRJ_SID.getCode()));
        final List<MenuSlaveVO> menus =
                menuSlaveStdDataSvc.queryBySysPrjIds(sysPrjIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(menus)) {
            return Collections.emptyMap();
        }
        return menus.stream()
                .collect(Collectors.groupingBy(MenuSlaveVO::getSysPrjId));
    }

    protected Map<Long, List<SysApiSlaveVO>> getSysApis(final List<Long> sysPrjIds,
                                                        final StdDataQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(SysApi.ShowColumn.SYS_PRJ_SID.getCode()));
        final List<SysApiSlaveVO> sysApis =
                sysApiSlaveStdDataSvc.queryBySysPrjIds(sysPrjIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysApis)) {
            return Collections.emptyMap();
        }
        return sysApis.stream()
                .collect(Collectors.groupingBy(SysApiSlaveVO::getSysPrjId));
    }

    protected Map<Long, List<SysResSlaveVO>> getSysResources(final List<Long> sysPrjIds,
                                                             final StdDataQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(SysRes.ShowColumn.SYS_PRJ_SID.getCode()));
        final List<SysResSlaveVO> sysResources =
                sysResSlaveStdDataSvc.queryBySysPrjIds(sysPrjIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResources)) {
            return Collections.emptyMap();
        }
        return sysResources.stream()
                .collect(Collectors.groupingBy(SysResSlaveVO::getSysPrjId));
    }

    @Override
    public SysPrjSlaveVO transferToStdDataVO(final SysPrj entity) {
        final SysPrjSlaveVO stdVO = new SysPrjSlaveVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setCode(entity.getCode());
        stdVO.setName(entity.getName());
        stdVO.setMemo(entity.getMemo());
        stdVO.setStatus(entity.getStatus());
        return stdVO;
    }
}
