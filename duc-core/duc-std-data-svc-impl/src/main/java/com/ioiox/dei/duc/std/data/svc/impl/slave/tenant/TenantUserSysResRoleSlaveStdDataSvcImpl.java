package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserSysResRole;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysResRoleR2ResSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("tenantUserSysResRoleSlaveStdDataSvc")
public class TenantUserSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<TenantUserSysResRoleSlaveVO, TenantUserSysResRole, TenantUserSysResRoleQueryParam>
        implements TenantUserSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserSysResRoleSlaveDbSvc")
    private TenantUserSysResRoleSlaveDbSvc tenantUserSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserSysResRoleR2ResSlaveDbSvc")
    private TenantUserSysResRoleR2ResSlaveDbSvc tenantUserSysResRoleR2ResSlaveDbSvc;

    @Override
    public List<TenantUserSysResRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                              final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tenantIds)) {
            return Collections.emptyList();
        }
        final TenantUserSysResRoleQueryParam queryParam = new TenantUserSysResRoleQueryParam.Builder()
                .tenantIds(tenantIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public TenantUserSysResRoleSlaveVO queryByPk(final Long pk,
                                                 final SysResRoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<TenantUserSysResRoleSlaveVO> sysResRoles =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResRoles)) {
            return null;
        }
        return sysResRoles.get(0);
    }

    @Override
    public List<TenantUserSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                        final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final TenantUserSysResRoleQueryParam queryParam = new TenantUserSysResRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserSysResRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<TenantUserSysResRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return tenantUserSysResRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> sysResRoleIds) {
        return tenantUserSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(sysResRoleIds);
    }

    @Override
    public TenantUserSysResRoleSlaveVO transferToStdDataVO(final TenantUserSysResRole entity) {
        final TenantUserSysResRoleSlaveVO sysResRole = new TenantUserSysResRoleSlaveVO();
        assembleSimpleRoleAttrs(sysResRole, entity);
        sysResRole.setTenantId(entity.getTenantSid());
        return sysResRole;
    }
}
