package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpRole;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleR2SysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserTmpRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("tenantUserTmpRoleSlaveStdDataSvc")
public class TenantUserTmpRoleSlaveStdDataSvcImpl
        extends BaseRoleSlaveStdDataSvc<TenantUserTmpRoleSlaveVO, TenantUserTmpRole, TenantUserTmpRoleQueryParam>
        implements TenantUserTmpRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserTmpRoleSlaveDbSvc")
    private TenantUserTmpRoleSlaveDbSvc tenantUserTmpRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleR2MenuSlaveDbSvc")
    private TenantUserTmpRoleR2MenuSlaveDbSvc tenantUserTmpRoleR2MenuSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleR2MenuSysApiSlaveDbSvc")
    private TenantUserTmpRoleR2MenuSysApiSlaveDbSvc tenantUserTmpRoleR2MenuSysApiSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleR2SysApiSlaveDbSvc")
    private TenantUserTmpRoleR2SysApiSlaveDbSvc tenantUserTmpRoleR2SysApiSlaveDbSvc;

    @Override
    public List<TenantUserTmpRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                           final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tenantIds)) {
            return Collections.emptyList();
        }
        final TenantUserTmpRoleQueryParam queryParam = new TenantUserTmpRoleQueryParam.Builder()
                .tenantIds(tenantIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public TenantUserTmpRoleSlaveVO queryByPk(final Long pk,
                                              final RoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<TenantUserTmpRoleSlaveVO> tmpRoles =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpRoles)) {
            return null;
        }
        return tmpRoles.get(0);
    }

    @Override
    public List<TenantUserTmpRoleSlaveVO> queryByPks(final List<Long> pks,
                                                     final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final TenantUserTmpRoleQueryParam queryParam = new TenantUserTmpRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserTmpRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<TenantUserTmpRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return tenantUserTmpRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getMenuIds(final List<Long> tmpRoleIds) {
        return tenantUserTmpRoleR2MenuSlaveDbSvc.getGroupedMenuIds(tmpRoleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiMappingIds(final List<Long> tmpRoleIds) {
        return tenantUserTmpRoleR2MenuSysApiSlaveDbSvc.getGroupedMappingSids(tmpRoleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> tmpRoleIds) {
        return tenantUserTmpRoleR2SysApiSlaveDbSvc.getGroupedSysApiSids(tmpRoleIds);
    }

    @Override
    protected void assembleMenus(final TenantUserTmpRoleSlaveVO tmpRole, final List<MenuSlaveVO> menus) {
        tmpRole.setMenus(menus);
    }

    @Override
    protected void assembleSysApiMappings(final TenantUserTmpRoleSlaveVO tmpRole, final List<MenuSysApiMappingSlaveStdVO> sysApiMappings) {
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            tmpRole.setSysApiMappings(Collections.emptyMap());
        } else {
            tmpRole.setSysApiMappings(sysApiMappings.stream()
                    .collect(Collectors.groupingBy(MenuSysApiMappingSlaveStdVO::getMenuId)));
        }
    }

    @Override
    protected void assembleMenuSysApis(final TenantUserTmpRoleSlaveVO tmpRole, final List<SysApiSlaveVO> menuSysApis) {
        tmpRole.setMenuSysApis(menuSysApis);
    }

    @Override
    protected void assembleSysApis(final TenantUserTmpRoleSlaveVO tmpRole, final List<SysApiSlaveVO> sysApis) {
        tmpRole.setSysApis(sysApis);
    }

    @Override
    public TenantUserTmpRoleSlaveVO transferToStdDataVO(final TenantUserTmpRole entity) {
        final TenantUserTmpRoleSlaveVO tmpRole = new TenantUserTmpRoleSlaveVO();
        assembleTmpRoleAttrs(tmpRole, entity);
        tmpRole.setTenantId(entity.getTenantSid());
        return tmpRole;
    }
}
