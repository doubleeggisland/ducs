package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.duc.beans.entity.UserAcct;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userAcctSlaveStdDataSvc")
public class UserAcctSlaveStdDataSvcImpl
        extends BaseDeiSlaveStdDataSvc<UserAcctSlaveStdVO, UserAcct>
        implements UserAcctSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctSlaveDbSvc")
    private UserAcctSlaveDbSvc userAcctSlaveDbSvc;



    @Override
    public UserAcctSlaveStdVO transferToStdDataVO(final UserAcct entity) {
        final UserAcctSlaveStdVO stdVO = new UserAcctSlaveStdVO();
        assembleCommonAttrs(stdVO, entity);
        return stdVO;
    }
}