package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserRole;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleR2SysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("tenantUserRoleSlaveStdDataSvc")
public class TenantUserRoleSlaveStdDataSvcImpl
        extends BaseRoleSlaveStdDataSvc<TenantUserRoleSlaveVO, TenantUserRole, TenantUserRoleQueryParam>
        implements TenantUserRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserRoleSlaveDbSvc")
    private TenantUserRoleSlaveDbSvc tenantUserRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleR2MenuSlaveDbSvc")
    private TenantUserRoleR2MenuSlaveDbSvc tenantUserRoleR2MenuSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleR2MenuSysApiSlaveDbSvc")
    private TenantUserRoleR2MenuSysApiSlaveDbSvc tenantUserRoleR2MenuSysApiSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleR2SysApiSlaveDbSvc")
    private TenantUserRoleR2SysApiSlaveDbSvc tenantUserRoleR2SysApiSlaveDbSvc;

    @Override
    public List<TenantUserRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                        final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tenantIds)) {
            return Collections.emptyList();
        }
        final TenantUserRoleQueryParam queryParam = new TenantUserRoleQueryParam.Builder()
                .tenantIds(tenantIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public TenantUserRoleSlaveVO queryByPk(final Long pk, final RoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<TenantUserRoleSlaveVO> tenantUserRoles = queryByPKs(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(tenantUserRoles)) {
            return null;
        }
        return tenantUserRoles.get(0);
    }

    @Override
    public List<TenantUserRoleSlaveVO> queryByPKs(final List<Long> pks, final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final TenantUserRoleQueryParam queryParam = new TenantUserRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<TenantUserRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return tenantUserRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getMenuIds(final List<Long> roleIds) {
        return tenantUserRoleR2MenuSlaveDbSvc.getGroupedMenuIds(roleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiMappingIds(final List<Long> roleIds) {
        return tenantUserRoleR2MenuSysApiSlaveDbSvc.getGroupedMappingSids(roleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> roleIds) {
        return tenantUserRoleR2SysApiSlaveDbSvc.getGroupedSysApiSids(roleIds);
    }

    @Override
    public TenantUserRoleSlaveVO transferToStdDataVO(final TenantUserRole entity) {
        final TenantUserRoleSlaveVO role = new TenantUserRoleSlaveVO();
        assembleSimpleRoleAttrs(role, entity);
        role.setTenantId(entity.getTenantSid());
        return role;
    }
}
