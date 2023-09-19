package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserGrp;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserGrpSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2RoleSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2SysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2UserSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserGrpSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserGrpSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("tenantUserGrpSlaveStdDataSvc")
public class TenantUserGrpSlaveStdDataSvcImpl
        extends BaseUserGrpSlaveStdDataSvc<TenantUserRoleSlaveVO, TenantUserSysResRoleSlaveVO, TenantUserGrpSlaveVO, TenantUserGrp, TenantUserGrpQueryParam>
        implements TenantUserGrpSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserGrpSlaveDbSvc")
    private TenantUserGrpSlaveDbSvc tenantUserGrpSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserGrpR2RoleSlaveDbSvc")
    private TenantUserGrpR2RoleSlaveDbSvc tenantUserGrpR2RoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserGrpR2SysResRoleSlaveDbSvc")
    private TenantUserGrpR2SysResRoleSlaveDbSvc tenantUserGrpR2SysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserGrpR2UserSlaveDbSvc")
    private TenantUserGrpR2UserSlaveDbSvc tenantUserGrpR2UserSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleSlaveStdDataSvc")
    private TenantUserRoleSlaveStdDataSvc tenantUserRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserSysResRoleSlaveStdDataSvc")
    private TenantUserSysResRoleSlaveStdDataSvc tenantUserSysResRoleSlaveStdDataSvc;

    @Override
    public List<TenantUserGrpSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                       final UserGrpQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tenantIds)) {
            return Collections.emptyList();
        }
        final TenantUserGrpQueryParam queryParam = new TenantUserGrpQueryParam.Builder()
                .tenantIds(tenantIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    public TenantUserGrpSlaveVO queryByPk(final Long pk,
                                          final UserGrpQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<TenantUserGrpSlaveVO> userGrps =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(userGrps)) {
            return null;
        }
        return userGrps.get(0);
    }

    @Override
    public List<TenantUserGrpSlaveVO> queryByPks(final List<Long> pks,
                                                 final UserGrpQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final TenantUserGrpQueryParam queryParam = new TenantUserGrpQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserGrpSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<TenantUserGrp> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return tenantUserGrpSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getRoleIds(final List<Long> userGrpIds) {
        return tenantUserGrpR2RoleSlaveDbSvc.getGroupedRoleIds(userGrpIds);
    }

    @Override
    protected List<TenantUserRoleSlaveVO> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg) {
        return tenantUserRoleSlaveStdDataSvc.queryByPKs(roleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getSysResRoleIds(final List<Long> userGrpIds) {
        return tenantUserGrpR2SysResRoleSlaveDbSvc.getGroupedSysResRoleIds(userGrpIds);
    }

    @Override
    protected List<TenantUserSysResRoleSlaveVO> querySysResRolesByPks(final List<Long> sysResRoleIds, final SysResRoleQueryCfg queryCfg) {
        return tenantUserSysResRoleSlaveStdDataSvc.queryByPks(sysResRoleIds, queryCfg);
    }

    @Override
    public TenantUserGrpSlaveVO transferToStdDataVO(TenantUserGrp entity) {
        final TenantUserGrpSlaveVO userGrp = new TenantUserGrpSlaveVO();
        assembleCommonAttrs(userGrp, entity);
        userGrp.setTenantId(entity.getTenantSid());
        return userGrp;
    }
}
