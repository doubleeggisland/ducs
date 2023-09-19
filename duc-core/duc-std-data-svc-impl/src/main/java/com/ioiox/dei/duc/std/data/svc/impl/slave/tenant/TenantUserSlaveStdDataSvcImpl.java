package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUser;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.*;
import com.ioiox.dei.duc.db.service.slave.tenant.*;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.UserSysPrjPrivilegeSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserSlaveStdDataSvc")
public class TenantUserSlaveStdDataSvcImpl
        extends BaseUserSlaveStdDataSvc<TenantUserRoleSlaveVO, TenantUserSysResRoleSlaveVO, TenantUserTmpRoleSlaveVO, TenantUserTmpSysResRoleSlaveVO, TenantUserGrpSlaveVO, TenantUserSlaveVO, TenantUser, TenantUserQueryParam>
        implements TenantUserSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserSlaveDbSvc")
    private TenantUserSlaveDbSvc tenantUserSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserR2RoleSlaveDbSvc")
    private TenantUserR2RoleSlaveDbSvc tenantUserR2RoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserR2SysResRoleSlaveDbSvc")
    private TenantUserR2SysResRoleSlaveDbSvc tenantUserR2SysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserR2TmpRoleSlaveDbSvc")
    private TenantUserR2TmpRoleSlaveDbSvc tenantUserR2TmpRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserR2TmpSysResRoleSlaveDbSvc")
    private TenantUserR2TmpSysResRoleSlaveDbSvc tenantUserR2TmpSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleSlaveStdDataSvc")
    private TenantUserRoleSlaveStdDataSvc tenantUserRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserGrpR2UserSlaveDbSvc")
    private TenantUserGrpR2UserSlaveDbSvc tenantUserGrpR2UserSlaveDbSvc;

    @Autowired
    @Qualifier("tenantUserSysResRoleSlaveStdDataSvc")
    private TenantUserSysResRoleSlaveStdDataSvc tenantUserSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleSlaveStdDataSvc")
    private TenantUserTmpRoleSlaveStdDataSvc tenantUserTmpRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleSlaveStdDataSvc")
    private TenantUserTmpSysResRoleSlaveStdDataSvc tenantUserTmpSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserGrpSlaveStdDataSvc")
    private TenantUserGrpSlaveStdDataSvc tenantUserGrpSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserSysPrjPrivilegeSlaveStdDataSvc")
    private UserSysPrjPrivilegeSlaveStdDataSvc tenantUserSysPrjPrivilegeSlaveStdDataSvc;

    @Override
    public List<TenantUserSlaveVO> queryByTenantIds(final List<Long> tenantIds,
                                                    final UserQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tenantIds)) {
            return Collections.emptyList();
        }
        final TenantUserQueryParam queryParam = new TenantUserQueryParam.Builder()
                .tenantIds(tenantIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<TenantUser> findByParams(final Map<String, Object> queryParams,
                                            final List<String> showColumns) {
        return tenantUserSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getRoleIds(final List<Long> tenantUserIds) {
        return tenantUserR2RoleSlaveDbSvc.getGroupedRoleIds(tenantUserIds);
    }

    @Override
    protected List<TenantUserRoleSlaveVO> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg) {
        return tenantUserRoleSlaveStdDataSvc.queryByPKs(roleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getSysResRoleIds(final List<Long> tenantUserIds) {
        return tenantUserR2SysResRoleSlaveDbSvc.getGroupedSysResRoleIds(tenantUserIds);
    }

    @Override
    protected List<TenantUserSysResRoleSlaveVO> querySysResRolesByPks(final List<Long> sysResRoleIds, final SysResRoleQueryCfg queryCfg) {
        return tenantUserSysResRoleSlaveStdDataSvc.queryByPks(sysResRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getTmpRoleIds(final List<Long> tenantUserIds) {
        return tenantUserR2TmpRoleSlaveDbSvc.getGroupedTmpRoleIds(tenantUserIds);
    }

    @Override
    protected List<TenantUserTmpRoleSlaveVO> queryTmpRolesByPks(final List<Long> tmpRoleIds, final RoleQueryCfg queryCfg) {
        return tenantUserTmpRoleSlaveStdDataSvc.queryByPks(tmpRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getTmpSysResRoleIds(final List<Long> tenantUserIds) {
        return tenantUserR2TmpSysResRoleSlaveDbSvc.getGroupedTmpSysResRoleIds(tenantUserIds);
    }

    @Override
    protected List<TenantUserTmpSysResRoleSlaveVO> queryTmpSysResRolesByPks(final List<Long> tmpSysResRoleIds, final SysResRoleQueryCfg queryCfg) {
        return tenantUserTmpSysResRoleSlaveStdDataSvc.queryByPks(tmpSysResRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getUserGrpIds(final List<Long> tenantUserIds) {
        return tenantUserGrpR2UserSlaveDbSvc.getGroupedUserGrpIds(tenantUserIds);
    }

    @Override
    protected List<TenantUserGrpSlaveVO> queryUserGrpsByPks(final List<Long> userGrpIds, final UserGrpQueryCfg queryCfg) {
        return tenantUserGrpSlaveStdDataSvc.queryByPks(userGrpIds, queryCfg);
    }

    @Override
    protected List<UserSysPrjPrivilegeSlaveVO> querySysPrjPrivilegesByUserIds(final List<Long> tenantUserIds, final UserSysPrjPrivilegeQueryCfg queryCfg) {
        return tenantUserSysPrjPrivilegeSlaveStdDataSvc.queryByUserIds(tenantUserIds, queryCfg);
    }

    @Override
    public TenantUserSlaveVO transferToStdDataVO(final TenantUser entity) {
        final TenantUserSlaveVO tenantUser = new TenantUserSlaveVO();
        assembleCommonAttrs(tenantUser, entity);
        tenantUser.setTenantId(entity.getTenantSid());
        return tenantUser;
    }
}
