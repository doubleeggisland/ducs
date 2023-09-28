package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpRole;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeTmpRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeTmpRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeTmpRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeTmpRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeTmpRoleMasterStdDataSvc")
public class EmployeeTmpRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<EmployeeTmpRoleMasterVO, EmployeeTmpRoleUpdatableObj, EmployeeTmpRoleUpdateCtx, EmployeeTmpRoleDelParam, EmployeeTmpRoleSlaveVO, EmployeeTmpRole>
        implements EmployeeTmpRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(EmployeeTmpRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("employeeTmpRoleMasterDbSvc")
    private EmployeeTmpRoleMasterDbSvc employeeTmpRoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeTmpRoleSlaveStdDataSvc")
    private EmployeeTmpRoleSlaveStdDataSvc employeeTmpRoleSlaveStdDataSvc;

    private final EmployeeTmpRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new EmployeeTmpRoleUpdatableAttrsAnalyser();

    @Override
    protected EmployeeTmpRoleSlaveVO getExistingRole(final Long id) {
        return employeeTmpRoleSlaveStdDataSvc.queryByPk(id,
                new RoleQueryCfg.Builder()
                        .needMenus(DeiGlobalConstant.FLAG_YES)
                        .menuQueryCfg(new MenuQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysApiMappings(DeiGlobalConstant.FLAG_YES)
                        .sysApiMappingQueryCfg(new MenuSysApiMappingQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysApis(DeiGlobalConstant.FLAG_YES)
                        .sysApiQueryCfg(new DefaultStdDataQueryCfg.Builder()
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
    protected List<EmployeeTmpRoleSlaveVO> queryExistingRoles(final EmployeeTmpRoleDelParam delParam) {
        final EmployeeTmpRoleQueryParam queryParam = new EmployeeTmpRoleQueryParam.Builder()
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return employeeTmpRoleSlaveStdDataSvc.queryByParam(queryParam,
                new RoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected EmployeeTmpRoleUpdateCtx getUpdateContext(final EmployeeTmpRoleMasterVO tmpRole,
                                                        final EmployeeTmpRoleSlaveVO existingTmpRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(tmpRole, existingTmpRole);
    }

    @Override
    protected void doSave(final EmployeeTmpRole newEntity) {
        employeeTmpRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final EmployeeTmpRole example) {
        return employeeTmpRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeTmpRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignMenusToRole(final List<Long> menuIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menus to EmployeeTmpRole =====> menuIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(menuIds), tmpRoleId));
        }
        return employeeTmpRoleMasterDbSvc.assignMenusToTmpRole(menuIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from EmployeeTmpRole =====> menuIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(menuIds), tmpRoleId));
        }
        return employeeTmpRoleMasterDbSvc.removeMenusFromTmpRoles(menuIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from EmployeeTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return employeeTmpRoleMasterDbSvc.removeMenusFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    protected int assignMenuSysApisToRole(final List<Long> sysApiMappingIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menuSysApis to EmployeeTmpRole =====> sysApiMappingIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), tmpRoleId));
        }
        return employeeTmpRoleMasterDbSvc.assignMenuSysApisToTmpRole(sysApiMappingIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenuSysApisFromRole(final List<Long> sysApiMappingIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from EmployeeTmpRole =====> sysApiMappingIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), tmpRoleId));
        }
        return employeeTmpRoleMasterDbSvc.removeMenuSysApisFromTmpRoles(sysApiMappingIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeMenuSysApisFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from EmployeeTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return employeeTmpRoleMasterDbSvc.removeMenuSysApisFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysApis to EmployeeTmpRole =====> sysApiIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), tmpRoleId));
        }
        return employeeTmpRoleMasterDbSvc.assignSysApisToTmpRole(sysApiIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from EmployeeTmpRole =====> sysApiIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), tmpRoleId));
        }
        return employeeTmpRoleMasterDbSvc.removeSysApisFromTmpRoles(sysApiIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from EmployeeTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return employeeTmpRoleMasterDbSvc.removeSysApisFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    public EmployeeTmpRole toNewEntity(final EmployeeTmpRoleMasterVO tmpRole) {
        final EmployeeTmpRole newEntity = new EmployeeTmpRole();
        assembleCommonAttrsOnInsert(newEntity, tmpRole);
        assembleSimpleTmpRoleAttrs(newEntity, tmpRole);
        return newEntity;
    }

    @Override
    public EmployeeTmpRole toUpdatableObj(EmployeeTmpRoleUpdatableObj updatableObj) {
        final EmployeeTmpRole example = new EmployeeTmpRole();
        assembleTmpRoleUpdatableAttrs(example, updatableObj);
        return example;
    }
}
