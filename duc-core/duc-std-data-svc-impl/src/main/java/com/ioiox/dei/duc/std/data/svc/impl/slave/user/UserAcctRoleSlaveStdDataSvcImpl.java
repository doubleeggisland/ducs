package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2SysApiSlaveDbSvc;
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
        extends BaseRoleSlaveStdDataSvc<UserAcctRoleSlaveVO, UserAcctRole, UserAcctRoleQueryParam>
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

    @Autowired
    @Qualifier("userAcctRoleR2SysApiSlaveDbSvc")
    private UserAcctRoleR2SysApiSlaveDbSvc userAcctRoleR2SysApiSlaveDbSvc;

    @Override
    public UserAcctRoleSlaveVO queryByPk(final Long pk, final RoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<UserAcctRoleSlaveVO> userAcctRoles = queryByPKs(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(userAcctRoles)) {
            return null;
        }
        return userAcctRoles.get(0);
    }

    @Override
    public List<UserAcctRoleSlaveVO> queryByPKs(final List<Long> pks, final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final UserAcctRoleQueryParam queryParam = new UserAcctRoleQueryParam.Builder()
                .pks(pks)
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
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> roleIds) {
        return userAcctRoleR2SysApiSlaveDbSvc.getGroupedSysApiSids(roleIds);
    }

    @Override
    protected void assembleMenus(final UserAcctRoleSlaveVO role,
                                 final List<MenuSlaveVO> menus) {
        role.setMenus(menus);
    }

    @Override
    protected void assembleSysApiMappings(final UserAcctRoleSlaveVO role,
                                          final List<MenuSysApiMappingSlaveStdVO> sysApiMappings) {
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            role.setSysApiMappings(Collections.emptyMap());
        } else {
            role.setSysApiMappings(sysApiMappings.stream()
                    .collect(Collectors.groupingBy(MenuSysApiMappingSlaveStdVO::getMenuId)));
        }
    }

    @Override
    protected void assembleMenuSysApis(final UserAcctRoleSlaveVO role, final List<SysApiSlaveVO> menuSysApis) {
        role.setMenuSysApis(menuSysApis);
    }

    @Override
    protected void assembleSysApis(final UserAcctRoleSlaveVO role, final List<SysApiSlaveVO> sysApis) {
        role.setSysApis(sysApis);
    }

    @Override
    public UserAcctRoleSlaveVO transferToStdDataVO(final UserAcctRole entity) {
        final UserAcctRoleSlaveVO stdVO = new UserAcctRoleSlaveVO();
        assembleRoleAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}
