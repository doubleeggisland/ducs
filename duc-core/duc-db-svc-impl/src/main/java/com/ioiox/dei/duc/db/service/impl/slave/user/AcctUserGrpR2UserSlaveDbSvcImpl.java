package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserGrpR2UserSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2UserSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("acctUserGrpR2UserSlaveDbSvc")
public class AcctUserGrpR2UserSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2User, AcctUserGrpR2UserSlaveMapper>
        implements AcctUserGrpR2UserSlaveDbSvc {

    @Autowired
    private AcctUserGrpR2UserSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedUserGrpIds(final List<Long> acctUserIds) {
        if (DeiCollectionUtil.isEmpty(acctUserIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (acctUserIds.size() > 1) {
            conditionsHolder.addQueryCondition("userSids", acctUserIds);
        } else {
            conditionsHolder.addQueryCondition("userSid", acctUserIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2User.ShowColumn.USER_SID.getCode(), UserGrpR2User.ShowColumn.USER_GROUP_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedAcctUserIds(final List<Long> acctUserGrpIds) {
        if (DeiCollectionUtil.isEmpty(acctUserGrpIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (acctUserGrpIds.size() > 1) {
            conditionsHolder.addQueryCondition("userGrpSids", acctUserGrpIds);
        } else {
            conditionsHolder.addQueryCondition("userGrpSid", acctUserGrpIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2User.ShowColumn.USER_SID.getCode(), UserGrpR2User.ShowColumn.USER_GROUP_SID.getCode()));
    }

    @Override
    protected AcctUserGrpR2UserSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与用户关联表";
    }
}
