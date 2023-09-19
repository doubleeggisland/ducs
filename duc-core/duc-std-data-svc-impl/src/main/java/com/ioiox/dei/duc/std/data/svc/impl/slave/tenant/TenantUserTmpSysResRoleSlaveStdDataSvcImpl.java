package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpSysResRole;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpSysResRoleR2ResSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpSysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserTmpSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("tenantUserTmpSysResRoleSlaveStdDataSvc")
public class TenantUserTmpSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<TenantUserTmpSysResRoleSlaveVO, TenantUserTmpSysResRole, TenantUserTmpSysResRoleQueryParam>
        implements TenantUserTmpSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleSlaveDbSvc")
    private TenantUserTmpSysResRoleSlaveDbSvc tenantUserTmpSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleR2ResSlaveDbSvc")
    private TenantUserTmpSysResRoleR2ResSlaveDbSvc tenantUserTmpSysResRoleR2ResSlaveDbSvc;

    @Override
    public List<TenantUserTmpSysResRoleSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                                 final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tenantIds)) {
            return Collections.emptyList();
        }
        final TenantUserTmpSysResRoleQueryParam queryParam = new TenantUserTmpSysResRoleQueryParam.Builder()
                .tenantIds(tenantIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public TenantUserTmpSysResRoleSlaveVO queryByPk(final Long pk,
                                                    final SysResRoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<TenantUserTmpSysResRoleSlaveVO> tmpSysResRoles =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpSysResRoles)) {
            return null;
        }
        return tmpSysResRoles.get(0);
    }

    @Override
    public List<TenantUserTmpSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                           final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final TenantUserTmpSysResRoleQueryParam queryParam = new TenantUserTmpSysResRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserTmpSysResRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<TenantUserTmpSysResRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return tenantUserTmpSysResRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected void assembleSysResources(final TenantUserTmpSysResRoleSlaveVO tmpSysResRole, final List<SysResSlaveVO> sysResources) {
        tmpSysResRole.setSysResources(sysResources);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> tmpSysResRoleIds) {
        return tenantUserTmpSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(tmpSysResRoleIds);
    }

    @Override
    public TenantUserTmpSysResRoleSlaveVO transferToStdDataVO(final TenantUserTmpSysResRole entity) {
        final TenantUserTmpSysResRoleSlaveVO tmpSysResRole = new TenantUserTmpSysResRoleSlaveVO();
        assembleTmpRoleAttrs(tmpSysResRole, entity);
        tmpSysResRole.setTenantId(entity.getTenantSid());
        return tmpSysResRole;
    }
}
