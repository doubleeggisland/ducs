package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.service.master.user.AcctUserSysPrjPrivilegeMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserSysPrjPrivilegeMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("acctUserSysPrjPrivilegeMasterStdDataSvc")
public class AcctUserSysPrjPrivilegeMasterStdDataSvcImpl
        extends BaseUserSysPrjPrivilegeMasterStdDataSvc {

    @Autowired
    @Qualifier("acctUserSysPrjPrivilegeMasterDbSvc")
    private AcctUserSysPrjPrivilegeMasterDbSvc acctUserSysPrjPrivilegeMasterDbSvc;

    @Override
    protected int doSave(final List<UserSysPrjPrivilege> newEntities) {
        return acctUserSysPrjPrivilegeMasterDbSvc.dbInsert(newEntities);
    }

    @Override
    protected int doUpdate(final UserSysPrjPrivilege example) {
        return acctUserSysPrjPrivilegeMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return acctUserSysPrjPrivilegeMasterDbSvc.deleteByParams(deleteParams);
    }
}
