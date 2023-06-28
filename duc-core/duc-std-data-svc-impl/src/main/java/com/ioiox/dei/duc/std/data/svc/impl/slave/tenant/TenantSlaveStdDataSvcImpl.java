package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tenantSlaveStdDataSvc")
public class TenantSlaveStdDataSvcImpl
        implements TenantSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantSlaveDbSvc")
    private TenantSlaveDbSvc tenantSlaveDbSvc;

    @Override
    public int countByParam(final TenantQueryParam queryParam) {
        return 0;
    }

    @Override
    public List<TenantSlaveStdVO> queryByParam(final TenantQueryParam queryParam,
                                               final TenantQueryCfg queryCfg) {
        return null;
    }
}
