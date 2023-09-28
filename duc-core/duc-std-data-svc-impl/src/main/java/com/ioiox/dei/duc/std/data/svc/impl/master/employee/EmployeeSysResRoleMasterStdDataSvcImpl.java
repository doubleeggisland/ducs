package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeSysResRole;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeSysResRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeSysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeSysResRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeSysResRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeSysResRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("employeeSysResRoleMasterStdDataSvc")
public class EmployeeSysResRoleMasterStdDataSvcImpl
        extends BaseSysResRoleMasterStdDataSvc<EmployeeSysResRoleMasterVO, EmployeeSysResRoleUpdatableObj, EmployeeSysResRoleUpdateCtx, EmployeeSysResRoleDelParam, EmployeeSysResRoleSlaveVO, EmployeeSysResRole>
        implements EmployeeSysResRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(EmployeeSysResRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("employeeSysResRoleMasterDbSvc")
    private EmployeeSysResRoleMasterDbSvc employeeSysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeSysResRoleSlaveStdDataSvc")
    private EmployeeSysResRoleSlaveStdDataSvc employeeSysResRoleSlaveStdDataSvc;

    private final EmployeeSysResRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new EmployeeSysResRoleUpdatableAttrsAnalyser();

    @Override
    protected EmployeeSysResRoleSlaveVO getExistingSysResRole(final Long id) {
        return employeeSysResRoleSlaveStdDataSvc.queryByPk(id,
                new SysResRoleQueryCfg.Builder()
                        .needSysResources(DeiGlobalConstant.FLAG_YES)
                        .sysResQueryCfg(new DefaultStdDataQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                SimpleRole.ShowColumn.CODE.getCode(), SimpleRole.ShowColumn.NAME.getCode(),
                                SimpleRole.ShowColumn.TYPE.getCode(), SimpleRole.ShowColumn.STATUS.getCode(),
                                SimpleRole.ShowColumn.MEMO.getCode(), SimpleRole.ShowColumn.SYS_PRJ_SID.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<EmployeeSysResRoleSlaveVO> queryExistingSysResRoles(final EmployeeSysResRoleDelParam delParam) {
        final EmployeeSysResRoleQueryParam queryParam = new EmployeeSysResRoleQueryParam.Builder()
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return employeeSysResRoleSlaveStdDataSvc.queryByParam(queryParam,
                new SysResRoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected EmployeeSysResRoleUpdateCtx getUpdateContext(final EmployeeSysResRoleMasterVO sysResRole,
                                                           final EmployeeSysResRoleSlaveVO existingSysResRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(sysResRole, existingSysResRole);
    }

    @Override
    protected void doSave(final EmployeeSysResRole newEntity) {
        employeeSysResRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final EmployeeSysResRole example) {
        return employeeSysResRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeSysResRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignSysResourcesToSysResRole(final List<Long> sysResIds, final Long sysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResources to EmployeeSysResRole =====> sysResIds: %s, sysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), sysResRoleId));
        }
        return employeeSysResRoleMasterDbSvc.assignSysResourcesToSysResRole(sysResIds, sysResRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResourcesFromSysResRole(final List<Long> sysResIds, final Long sysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from EmployeeSysResRole =====> sysResIds: %s, sysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), sysResRoleId));
        }
        return employeeSysResRoleMasterDbSvc.removeSysResourcesFromSysResRoles(sysResIds, Collections.singletonList(sysResRoleId));
    }

    @Override
    protected int removeSysResourcesFromSysResRoles(final List<Long> sysResRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from EmployeeSysResRoles ======> sysResRoleIds: %s", JsonUtil.toJsonStr(sysResRoleIds)));
        }
        return employeeSysResRoleMasterDbSvc.removeSysResourcesFromSysResRoles(null, sysResRoleIds);
    }

    @Override
    public EmployeeSysResRole toNewEntity(final EmployeeSysResRoleMasterVO sysResRole) {
        final EmployeeSysResRole newEntity = new EmployeeSysResRole();
        assembleCommonAttrsOnInsert(newEntity, sysResRole);
        assembleSimpleRoleAttrs(newEntity, sysResRole);
        return newEntity;
    }

    @Override
    public EmployeeSysResRole toUpdatableObj(final EmployeeSysResRoleUpdatableObj updatableObj) {
        final EmployeeSysResRole example = new EmployeeSysResRole();
        assembleRoleUpdatableAttrs(example, updatableObj);
        return example;
    }
}
