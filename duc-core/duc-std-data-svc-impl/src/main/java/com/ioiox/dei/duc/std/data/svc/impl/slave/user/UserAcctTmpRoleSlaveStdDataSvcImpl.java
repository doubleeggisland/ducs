package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpRole;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleR2SysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctTmpRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("userAcctTmpRoleSlaveStdDataSvc")
public class UserAcctTmpRoleSlaveStdDataSvcImpl
        extends BaseRoleSlaveStdDataSvc<UserAcctTmpRoleSlaveVO, UserAcctTmpRole, UserAcctTmpRoleQueryParam>
        implements UserAcctTmpRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctTmpRoleSlaveDbSvc")
    private UserAcctTmpRoleSlaveDbSvc userAcctTmpRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleR2MenuSlaveDbSvc")
    private UserAcctTmpRoleR2MenuSlaveDbSvc userAcctTmpRoleR2MenuSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleR2MenuSysApiSlaveDbSvc")
    private UserAcctTmpRoleR2MenuSysApiSlaveDbSvc userAcctTmpRoleR2MenuSysApiSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleR2SysApiSlaveDbSvc")
    private UserAcctTmpRoleR2SysApiSlaveDbSvc userAcctTmpRoleR2SysApiSlaveDbSvc;

    @Override
    public UserAcctTmpRoleSlaveVO queryByPk(final Long tmpRoleId,
                                            final RoleQueryCfg queryCfg) {
        if (Objects.isNull(tmpRoleId)) {
            return null;
        }
        final List<UserAcctTmpRoleSlaveVO> tmpRoles =
                queryByPks(Collections.singletonList(tmpRoleId), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpRoles)) {
            return null;
        }
        return tmpRoles.get(0);
    }

    @Override
    public List<UserAcctTmpRoleSlaveVO> queryByPks(final List<Long> tmpRoleIds,
                                                   final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tmpRoleIds)) {
            return Collections.emptyList();
        }
        final UserAcctTmpRoleQueryParam queryParam = new UserAcctTmpRoleQueryParam.Builder()
                .pks(tmpRoleIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return userAcctTmpRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserAcctTmpRole> findByParams(final Map<String, Object> queryParams,
                                                 final List<String> showColumns) {
        return userAcctTmpRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getMenuIds(final List<Long> tmpRoleIds) {
        return userAcctTmpRoleR2MenuSlaveDbSvc.getGroupedMenuIds(tmpRoleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiMappingIds(final List<Long> tmpRoleIds) {
        return userAcctTmpRoleR2MenuSysApiSlaveDbSvc.getGroupedMappingSids(tmpRoleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> tmpRoleIds) {
        return userAcctTmpRoleR2SysApiSlaveDbSvc.getGroupedSysApiSids(tmpRoleIds);
    }

    @Override
    protected void assembleMenus(final UserAcctTmpRoleSlaveVO tmpRole,
                                 final List<MenuSlaveVO> menus) {
        tmpRole.setMenus(menus);
    }

    @Override
    protected void assembleSysApiMappings(final UserAcctTmpRoleSlaveVO tmpRole,
                                          final List<MenuSysApiMappingSlaveStdVO> sysApiMappings) {
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            tmpRole.setSysApiMappings(Collections.emptyMap());
        } else {
            tmpRole.setSysApiMappings(sysApiMappings.stream()
                    .collect(Collectors.groupingBy(MenuSysApiMappingSlaveStdVO::getMenuId)));
        }
    }

    @Override
    protected void assembleMenuSysApis(final UserAcctTmpRoleSlaveVO tmpRole,
                                       final List<SysApiSlaveVO> menuSysApis) {
        tmpRole.setMenuSysApis(menuSysApis);
    }

    @Override
    protected void assembleSysApis(final UserAcctTmpRoleSlaveVO tmpRole,
                                   final List<SysApiSlaveVO> sysApis) {
        tmpRole.setSysApis(sysApis);
    }

    @Override
    public UserAcctTmpRoleSlaveVO transferToStdDataVO(final UserAcctTmpRole entity) {
        final UserAcctTmpRoleSlaveVO stdVO = new UserAcctTmpRoleSlaveVO();
        assembleTmpRoleAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}
