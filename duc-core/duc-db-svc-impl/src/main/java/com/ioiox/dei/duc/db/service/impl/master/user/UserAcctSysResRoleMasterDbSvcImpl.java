package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctSysResRoleMasterDbSvc")
public class UserAcctSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctSysResRole, UserAcctSysResRoleMasterMapper>
        implements UserAcctSysResRoleMasterDbSvc {

    @Autowired
    private UserAcctSysResRoleMasterMapper mapper;

    @Autowired
    @Qualifier("userAcctSysResRoleR2ResMasterDbSvc")
    private UserAcctSysResRoleR2ResMasterDbSvc userAcctSysResRoleR2ResMasterDbSvc;

    @Override
    public int assignSysResourcesToSysResRole(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime) {
        return userAcctSysResRoleR2ResMasterDbSvc.save(sysResSids, sysResRoleSid, operator, operateTime);
    }

    @Override
    public int removeSysResourcesFromSysResRoles(final List<Long> sysResSids, final List<Long> sysResRoleSids) {
        if (DeiCollectionUtil.isEmpty(sysResSids)
                && DeiCollectionUtil.isEmpty(sysResRoleSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(sysResSids)) {
            if (sysResSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResSids", sysResSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResSid", sysResSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(sysResRoleSids)) {
            if (sysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", sysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", sysResRoleSids.get(0));
            }
        }
        return userAcctSysResRoleR2ResMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected UserAcctSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户系统资源角色";
    }
}
