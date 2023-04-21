package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("userAcctRoleSlaveStdDataSvc")
public class UserAcctRoleSlaveStdDataSvcImpl
        extends BaseRoleSlaveStdDataSvc<UserAcctRoleSlaveStdVO, UserAcctRole, UserAcctRoleQueryParam>
        implements UserAcctRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctRoleSlaveDbSvc")
    private UserAcctRoleSlaveDbSvc userAcctRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctRoleR2MenuSlaveDbSvc")
    private UserAcctRoleR2MenuSlaveDbSvc userAcctRoleR2MenuSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctRoleR2MenuSysApiSlaveDbSvc")
    private UserAcctRoleR2MenuSysApiSlaveDbSvc userAcctRoleR2MenuSysApiSlaveDbSvc;

    @Override
    public UserAcctRoleSlaveStdVO queryByPk(final Long userId, final RoleQueryCfg queryCfg) {
        if (Objects.isNull(userId)) {
            return null;
        }
        final List<UserAcctRoleSlaveStdVO> userAcctRoles = queryByPKs(Collections.singletonList(userId), queryCfg);
        if (DeiCollectionUtil.isEmpty(userAcctRoles)) {
            return null;
        }
        return userAcctRoles.get(0);
    }

    @Override
    public List<UserAcctRoleSlaveStdVO> queryByPKs(final List<Long> userIds, final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        final UserAcctRoleQueryParam queryParam = new UserAcctRoleQueryParam.Builder()
                .pks(userIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return userAcctRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserAcctRole> findByParams(final Map<String, Object> queryParams,
                                              final List<String> showColumns) {
        return userAcctRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getMenuIds(final List<Long> roleIds) {
        return userAcctRoleR2MenuSlaveDbSvc.getGroupedMenuIds(roleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiMappingIds(final List<Long> roleIds) {
        return userAcctRoleR2MenuSysApiSlaveDbSvc.getGroupedMappingSids(roleIds);
    }

    @Override
    protected void assembleMenus(final UserAcctRoleSlaveStdVO role,
                                 final List<MenuSlaveStdVO> menus) {
        role.setMenus(menus);
    }

    @Override
    protected void assembleSysApiMappings(final UserAcctRoleSlaveStdVO role,
                                          final List<MenuSysApiMappingSlaveStdVO> sysApiMappings) {
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            role.setSysApiMappings(Collections.emptyMap());
        } else {
            role.setSysApiMappings(sysApiMappings.stream()
                    .collect(Collectors.groupingBy(MenuSysApiMappingSlaveStdVO::getMenuId)));
        }
    }

    @Override
    protected void assembleSysApis(final UserAcctRoleSlaveStdVO role, final List<SysApiSlaveStdVO> sysApis) {
        role.setSysApis(sysApis);
    }

    @Override
    public UserAcctRoleSlaveStdVO transferToStdDataVO(final UserAcctRole entity) {
        final UserAcctRoleSlaveStdVO stdVO = new UserAcctRoleSlaveStdVO();
        assembleRoleAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}
