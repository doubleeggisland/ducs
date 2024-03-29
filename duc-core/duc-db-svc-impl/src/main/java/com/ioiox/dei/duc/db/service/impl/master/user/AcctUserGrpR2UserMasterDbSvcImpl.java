package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserGrpR2UserMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpR2UserMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("acctUserGrpR2UserMasterDbSvc")
public class AcctUserGrpR2UserMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2User, AcctUserGrpR2UserMasterMapper>
        implements AcctUserGrpR2UserMasterDbSvc {

    @Autowired
    private AcctUserGrpR2UserMasterMapper mapper;

    @Override
    public int save(final List<Long> userGrpSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return dbInsert(UserGrpR2User.instances(userGrpSids, userAcctSid, operator, operateTime));
    }

    @Override
    protected AcctUserGrpR2UserMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与用户关联表";
    }
}
