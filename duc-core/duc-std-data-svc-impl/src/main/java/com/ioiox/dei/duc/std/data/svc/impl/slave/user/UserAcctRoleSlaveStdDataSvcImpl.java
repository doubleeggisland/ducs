package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSlaveDbSvc;
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
    @Qualifier("userAcctRoleR2SysApiSlaveDbSvc")
    private UserAcctRoleR2SysApiSlaveDbSvc userAcctRoleR2SysApiSlaveDbSvc;

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
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> roleIds) {
        return userAcctRoleR2SysApiSlaveDbSvc.getGroupedSysApiIds(roleIds);
    }

    @Override
    protected void assembleMenus(final UserAcctRoleSlaveStdVO role, final List<MenuSlaveStdVO> menus) {
        role.setMenus(menus);
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
