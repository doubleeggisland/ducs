package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeUserGrpR2UserSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpR2UserSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("employeeUserGrpR2UserSlaveDbSvc")
public class EmployeeUserGrpR2UserSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2User, EmployeeUserGrpR2UserSlaveMapper>
        implements EmployeeUserGrpR2UserSlaveDbSvc {

    @Autowired
    private EmployeeUserGrpR2UserSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedUserGrpIds(final List<Long> employeeIds) {
        if (DeiCollectionUtil.isEmpty(employeeIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (employeeIds.size() > 1) {
            conditionsHolder.addQueryCondition("userSids", employeeIds);
        } else {
            conditionsHolder.addQueryCondition("userSid", employeeIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2User.ShowColumn.USER_SID.getCode(), UserGrpR2User.ShowColumn.USER_GROUP_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedEmployeeIds(final List<Long> employeeUserGrpIds) {
        if (DeiCollectionUtil.isEmpty(employeeUserGrpIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (employeeUserGrpIds.size() > 1) {
            conditionsHolder.addQueryCondition("userGrpSids", employeeUserGrpIds);
        } else {
            conditionsHolder.addQueryCondition("userGrpSid", employeeUserGrpIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2User.ShowColumn.USER_SID.getCode(), UserGrpR2User.ShowColumn.USER_GROUP_SID.getCode()));
    }

    @Override
    protected EmployeeUserGrpR2UserSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与雇员关联表";
    }
}
