package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctTmpSysResRoleMasterDbSvc")
public class UserAcctTmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctTmpSysResRole, UserAcctTmpSysResRoleMasterMapper>
        implements UserAcctTmpSysResRoleMasterDbSvc {

    @Autowired
    private UserAcctTmpSysResRoleMasterMapper mapper;

    @Autowired
    @Qualifier("userAcctTmpSysResRoleR2ResMasterDbSvc")
    private UserAcctTmpSysResRoleR2ResMasterDbSvc userAcctTmpSysResRoleR2ResMasterDbSvc;

    @Override
    public int assignSysResourcesToTmpSysResRole(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime) {
        return userAcctTmpSysResRoleR2ResMasterDbSvc.save(sysResSids, tmpSysResRoleSid, operator, operateTime);
    }

    @Override
    public int removeSysResourcesFromTmpSysResRoles(final List<Long> sysResSids, final List<Long> tmpSysResRoleSids) {
        if (DeiCollectionUtil.isEmpty(sysResSids)
                && DeiCollectionUtil.isEmpty(tmpSysResRoleSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tmpSysResRoleSids)) {
            if (tmpSysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", tmpSysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", tmpSysResRoleSids.get(0));
            }
        }
        return userAcctTmpSysResRoleR2ResMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected UserAcctTmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时系统资源角色表";
    }
}
