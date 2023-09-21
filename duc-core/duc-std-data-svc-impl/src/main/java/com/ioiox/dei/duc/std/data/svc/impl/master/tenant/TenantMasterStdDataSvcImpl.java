package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.duc.beans.entity.Tenant;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUpdatableObj;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantMasterVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("tenantMasterStdDataSvc")
public class TenantMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<TenantMasterVO, TenantUpdatableObj, Tenant>
        implements TenantMasterStdDataSvc {

    @Autowired
    @Qualifier("tenantMasterDbSvc")
    private TenantMasterDbSvc tenantMasterDbSvc;

    public long save(final TenantMasterVO tenant) {
        return 0;
    }

    public boolean update(final TenantMasterVO tenant) {
        return false;
    }

    public int remove(final TenantDelParam delParam) {
        return 0;
    }

    @Override
    public Tenant toNewEntity(final TenantMasterVO tenant) {
        final Tenant newEntity = new Tenant();
        assembleCommonAttrsOnInsert(newEntity, tenant);
        newEntity.setCode(tenant.getCode());
        newEntity.setName(tenant.getName());
        newEntity.setMemo(tenant.getMemo());
        newEntity.setStatus(tenant.getStatus());
        newEntity.setLvl(tenant.getLvl());
        newEntity.setPid(tenant.getPid());
        newEntity.setRid(tenant.getRid());
        return newEntity;
    }

    @Override
    public Tenant toUpdatableObj(final TenantUpdatableObj updatableObj) {
        final Tenant example = new Tenant();
        assembleCommonUpdatableAttrs(example, updatableObj);
        if (Objects.nonNull(updatableObj.getCode())) {
            example.setCode(updatableObj.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getName())) {
            example.setName(updatableObj.getName().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getMemo())) {
            example.setMemo(updatableObj.getMemo().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getStatus())) {
            example.setStatus(updatableObj.getStatus().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getLvl())) {
            example.setLvl(updatableObj.getLvl().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getPid())) {
            example.setPid(updatableObj.getPid().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getRid())) {
            example.setRid(updatableObj.getRid().getNewVal());
        }
        return example;
    }
}
