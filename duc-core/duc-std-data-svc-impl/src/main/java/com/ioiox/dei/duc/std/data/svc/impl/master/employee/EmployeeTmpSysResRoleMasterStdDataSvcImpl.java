package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpSysResRole;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpSysResRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpSysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpSysResRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpSysResRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeTmpSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeTmpSysResRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeTmpSysResRoleMasterStdDataSvc")
public class EmployeeTmpSysResRoleMasterStdDataSvcImpl
        extends BaseSysResRoleMasterStdDataSvc<EmployeeTmpSysResRoleMasterVO, EmployeeTmpSysResRoleUpdatableObj, EmployeeTmpSysResRoleUpdateCtx, EmployeeTmpSysResRoleDelParam, EmployeeTmpSysResRoleSlaveVO, EmployeeTmpSysResRole>
        implements EmployeeTmpSysResRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(EmployeeTmpSysResRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("employeeTmpSysResRoleMasterDbSvc")
    private EmployeeTmpSysResRoleMasterDbSvc employeeTmpSysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeTmpSysResRoleSlaveStdDataSvc")
    private EmployeeTmpSysResRoleSlaveStdDataSvc employeeTmpSysResRoleSlaveStdDataSvc;

    private final EmployeeTmpSysResRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new EmployeeTmpSysResRoleUpdatableAttrsAnalyser();

    @Override
    protected EmployeeTmpSysResRoleSlaveVO getExistingSysResRole(final Long id) {
        return employeeTmpSysResRoleSlaveStdDataSvc.queryByPk(id,
                new SysResRoleQueryCfg.Builder()
                        .needSysResources(DeiGlobalConstant.FLAG_YES)
                        .sysResQueryCfg(new DefaultStdDataQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                SimpleRole.ShowColumn.CODE.getCode(), SimpleRole.ShowColumn.NAME.getCode(),
                                SimpleRole.ShowColumn.TYPE.getCode(), SimpleRole.ShowColumn.STATUS.getCode(),
                                SimpleRole.ShowColumn.MEMO.getCode(), SimpleRole.ShowColumn.SYS_PRJ_SID.getCode(),
                                SimpleRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), SimpleRole.ShowColumn.EFFECTIVE_START_DATE.getCode(),
                                SimpleRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), SimpleRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(),
                                SimpleRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), SimpleRole.ShowColumn.EFFECTIVE_END_TIME.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<EmployeeTmpSysResRoleSlaveVO> queryExistingSysResRoles(final EmployeeTmpSysResRoleDelParam delParam) {
        final EmployeeTmpSysResRoleQueryParam queryParam = new EmployeeTmpSysResRoleQueryParam.Builder()
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return employeeTmpSysResRoleSlaveStdDataSvc.queryByParam(queryParam,
                new SysResRoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected EmployeeTmpSysResRoleUpdateCtx getUpdateContext(final EmployeeTmpSysResRoleMasterVO tmpSysResRole,
                                                              final EmployeeTmpSysResRoleSlaveVO existingTmpSysResRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole);
    }

    @Override
    protected void doSave(final EmployeeTmpSysResRole newEntity) {
        employeeTmpSysResRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final EmployeeTmpSysResRole example) {
        return employeeTmpSysResRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeTmpSysResRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignSysResourcesToSysResRole(final List<Long> sysResIds, final Long tmpSysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResources to EmployeeTmpSysResRole =====> sysResIds: %s, tmpSysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), tmpSysResRoleId));
        }
        return employeeTmpSysResRoleMasterDbSvc.assignSysResourcesToTmpSysResRole(sysResIds, tmpSysResRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResourcesFromSysResRole(final List<Long> sysResIds, final Long tmpSysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from EmployeeTmpSysResRole =====> sysResIds: %s, tmpSysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), tmpSysResRoleId));
        }
        return employeeTmpSysResRoleMasterDbSvc.removeSysResourcesFromTmpSysResRoles(sysResIds, Collections.singletonList(tmpSysResRoleId));
    }

    @Override
    protected int removeSysResourcesFromSysResRoles(final List<Long> tmpSysResRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from EmployeeTmpSysResRoles ======> tmpSysResRoleIds: %s", JsonUtil.toJsonStr(tmpSysResRoleIds)));
        }
        return employeeTmpSysResRoleMasterDbSvc.removeSysResourcesFromTmpSysResRoles(null, tmpSysResRoleIds);
    }

    @Override
    public EmployeeTmpSysResRole toNewEntity(final EmployeeTmpSysResRoleMasterVO tmpSysResRole) {
        final EmployeeTmpSysResRole newEntity = new EmployeeTmpSysResRole();
        assembleCommonAttrsOnInsert(newEntity, tmpSysResRole);
        assembleSimpleTmpRoleAttrs(newEntity, tmpSysResRole);
        return newEntity;
    }

    @Override
    public EmployeeTmpSysResRole toUpdatableObj(final EmployeeTmpSysResRoleUpdatableObj updatableObj) {
        final EmployeeTmpSysResRole example = new EmployeeTmpSysResRole();
        assembleTmpRoleUpdatableAttrs(example, updatableObj);
        return example;
    }
}
