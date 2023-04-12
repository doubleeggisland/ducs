package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataQueryCfg;
import com.ioiox.dei.duc.beans.entity.MenuSysApiMapping;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.MenuSysApiMappingSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.MenuSysApiMappingSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.SysApiSlaveStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("menuSysApiMappingSlaveStdDataSvc")
public class MenuSysApiMappingSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<MenuSysApiMappingSlaveStdVO, MenuSysApiMapping>
        implements MenuSysApiMappingSlaveStdDataSvc {

    @Autowired
    @Qualifier("menuSysApiMappingSlaveDbSvc")
    private MenuSysApiMappingSlaveDbSvc menuSysApiMappingSlaveDbSvc;

    @Autowired
    @Qualifier("sysApiSlaveStdDataSvc")
    private SysApiSlaveStdDataSvc sysApiSlaveStdDataSvc;

    @Override
    public List<MenuSysApiMappingSlaveStdVO> queryByPks(final List<Long> sysApiMappingIds,
                                                        final MenuSysApiMappingQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysApiMappingIds)) {
            return Collections.emptyList();
        }
        final MenuSysApiMappingQueryParam queryParam = new MenuSysApiMappingQueryParam.Builder()
                .pks(sysApiMappingIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<MenuSysApiMappingSlaveStdVO> queryByMenuIds(final List<Long> menuIds,
                                                            final MenuSysApiMappingQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        final MenuSysApiMappingQueryParam queryParam = new MenuSysApiMappingQueryParam.Builder()
                .menuIds(menuIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public List<MenuSysApiMappingSlaveStdVO> queryByParam(final MenuSysApiMappingQueryParam queryParam,
                                                          final MenuSysApiMappingQueryCfg queryCfg) {
        final Map<String, Object> queryParams =
                Objects.isNull(queryParam) ? new HashMap<>() : queryParam.queryParams();
        if (Objects.nonNull(queryCfg)
                && StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApi())) {
            addShowColumnsIfNeeded(queryCfg, Collections.singletonList(MenuSysApiMapping.ShowColumn.SYS_API_SID.getCode()));
        }
        final List<String> showColumns =
                Objects.isNull(queryCfg) ? Collections.emptyList() : queryCfg.getShowColumns();
        final List<MenuSysApiMapping> entities =
                menuSysApiMappingSlaveDbSvc.findByParams(queryParams, showColumns);
        if (DeiCollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        final List<MenuSysApiMappingSlaveStdVO> sysApiMappings = new ArrayList<>(entities.size());
        final Set<Long> sysApiIds = new HashSet<>(entities.size());
        entities.forEach(entity -> {
            sysApiIds.add(entity.getSid());
            final MenuSysApiMappingSlaveStdVO sysApiMapping = transferToStdDataVO(entity);
            sysApiMappings.add(sysApiMapping);
        });

        if (Objects.nonNull(queryCfg)) {
            if (StringUtils.equals(DeiGlobalConstant.FLAG_YES, queryCfg.getNeedSysApi())) {
                final Map<Long, SysApiSlaveStdVO> sysApis = getSysApis(new ArrayList<>(sysApiIds), queryCfg.getSysApiQueryCfg());
                sysApiMappings.forEach(sysApiMapping -> {
                    if (sysApis.containsKey(sysApiMapping.getSysApiId())) {
                        sysApiMapping.setSysApi(sysApis.get(sysApiMapping.getSysApiId()));
                    }
                });
            }
        }
        return sysApiMappings;
    }

    private Map<Long, SysApiSlaveStdVO> getSysApis(final List<Long> sysApiIds,
                                                   final StdDataQueryCfg queryCfg) {
        addShowColumnsIfNeeded(queryCfg, Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()));
        final List<SysApiSlaveStdVO> sysApis = sysApiSlaveStdDataSvc.queryByPks(sysApiIds, queryCfg);
        if (DeiCollectionUtil.isEmpty(sysApiIds)) {
            return Collections.emptyMap();
        }
        return sysApis.stream()
                .collect(Collectors.toMap(SysApiSlaveStdVO::getId, sysApi -> sysApi));
    }

    @Override
    public MenuSysApiMappingSlaveStdVO transferToStdDataVO(final MenuSysApiMapping entity) {
        final MenuSysApiMappingSlaveStdVO stdVO = new MenuSysApiMappingSlaveStdVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setMenuId(entity.getMenuSid());
        stdVO.setSysApiId(entity.getSysApiSid());
        stdVO.setInteractForm(entity.getInteractForm());
        return stdVO;
    }
}
