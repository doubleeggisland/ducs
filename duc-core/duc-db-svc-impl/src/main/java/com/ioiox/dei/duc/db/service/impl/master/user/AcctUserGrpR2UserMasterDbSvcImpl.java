package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.master.user.AcctUserGrpR2UserMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpR2UserMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acctUserGrpR2UserMasterDbSvc")
public class AcctUserGrpR2UserMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2User, AcctUserGrpR2UserMasterMapper>
        implements AcctUserGrpR2UserMasterDbSvc {

    @Autowired
    private AcctUserGrpR2UserMasterMapper mapper;

    @Override
    protected AcctUserGrpR2UserMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与用户关联表";
    }
}
