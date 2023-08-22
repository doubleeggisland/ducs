package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.AcctUserGrp;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserGrpMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpR2RoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("acctUserGrpMasterDbSvc")
public class AcctUserGrpMasterDbSvcImpl
        extends BaseDeiMasterDbService<AcctUserGrp, AcctUserGrpMasterMapper>
        implements AcctUserGrpMasterDbSvc {

    @Autowired
    private AcctUserGrpMasterMapper mapper;

    @Autowired
    @Qualifier("acctUserGrpR2RoleMasterDbSvc")
    private AcctUserGrpR2RoleMasterDbSvc acctUserGrpR2RoleMasterDbSvc;

    @Autowired
    @Qualifier("acctUserGrpR2SysResRoleMasterDbSvc")
    private AcctUserGrpR2SysResRoleMasterDbSvc acctUserGrpR2SysResRoleMasterDbSvc;

    @Override
    public int assignRolesToUserGrp(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return acctUserGrpR2RoleMasterDbSvc.save(roleSids, userGrpSid, operator, operateTime);
    }

    @Override
    public int removeRolesFromUserGrps(final List<Long> roleSids, final List<Long> userGrpSids) {
        if (DeiCollectionUtil.isEmpty(roleSids)
                && DeiCollectionUtil.isEmpty(userGrpSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(roleSids)) {
            if (roleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", roleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", roleSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(userGrpSids)) {
            if (userGrpSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userGrpSids", userGrpSids);
            } else {
                conditionsHolder.addDeleteCondition("userGrpSid", userGrpSids.get(0));
            }
        }
        return acctUserGrpR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysResRolesToUserGrp(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return acctUserGrpR2SysResRoleMasterDbSvc.save(sysResRoleSids, userGrpSid, operator, operateTime);
    }

    @Override
    public int removeSysResRolesFromUserGrps(final List<Long> sysResRoleSids, final List<Long> userGrpSids) {
        if (DeiCollectionUtil.isEmpty(sysResRoleSids)
                && DeiCollectionUtil.isEmpty(userGrpSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(sysResRoleSids)) {
            if (sysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", sysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", sysResRoleSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(userGrpSids)) {
            if (userGrpSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userGrpSids", userGrpSids);
            } else {
                conditionsHolder.addDeleteCondition("userGrpSid", userGrpSids.get(0));
            }
        }
        return acctUserGrpR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected AcctUserGrpMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组表";
    }
}
