package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeRole;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeRoleMasterStdDataSvc")
public class EmployeeRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<EmployeeRoleMasterVO, EmployeeRoleUpdatableObj, EmployeeRoleUpdateCtx, EmployeeRoleDelParam, EmployeeRoleSlaveVO, EmployeeRole>
        implements EmployeeRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(EmployeeRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("employeeRoleMasterDbSvc")
    private EmployeeRoleMasterDbSvc employeeRoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeRoleSlaveStdDataSvc")
    private EmployeeRoleSlaveStdDataSvc employeeRoleSlaveStdDataSvc;

    private final EmployeeRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new EmployeeRoleUpdatableAttrsAnalyser();

    @Override
    protected EmployeeRoleSlaveVO getExistingRole(final Long id) {
        return employeeRoleSlaveStdDataSvc.queryByPk(
                id,
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
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build()
        );
    }

    @Override
    protected List<EmployeeRoleSlaveVO> queryExistingRoles(final EmployeeRoleDelParam delParam) {
        final EmployeeRoleQueryParam queryParam = new EmployeeRoleQueryParam.Builder()
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return employeeRoleSlaveStdDataSvc.queryByParam(
                queryParam,
                new RoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build()
        );
    }

    @Override
    protected EmployeeRoleUpdateCtx getUpdateContext(final EmployeeRoleMasterVO role, final EmployeeRoleSlaveVO existingRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(role, existingRole);
    }

    @Override
    protected void doSave(final EmployeeRole newEntity) {
        employeeRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final EmployeeRole example) {
        return employeeRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignMenusToRole(final List<Long> menuIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menus to EmployeeRole =====> menuIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(menuIds), roleId));
        }
        return employeeRoleMasterDbSvc.assignMenusToRole(menuIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from EmployeeRole =====> menuIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(menuIds), roleId));
        }
        return employeeRoleMasterDbSvc.removeMenusFromRoles(menuIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from EmployeeRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return employeeRoleMasterDbSvc.removeMenusFromRoles(null, roleIds);
    }

    @Override
    protected int assignMenuSysApisToRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menuSysApis to EmployeeRole =====> sysApiMappingIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), roleId));
        }
        return employeeRoleMasterDbSvc.assignMenuSysApisToRole(sysApiMappingIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenuSysApisFromRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from EmployeeRole =====> sysApiMappingIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), roleId));
        }
        return employeeRoleMasterDbSvc.removeMenuSysApisFromRoles(sysApiMappingIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeMenuSysApisFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from EmployeeRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return employeeRoleMasterDbSvc.removeMenuSysApisFromRoles(null, roleIds);
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysApis to EmployeeRole =====> sysApiIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), roleId));
        }
        return employeeRoleMasterDbSvc.assignSysApisToRole(sysApiIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from EmployeeRole =====> sysApiIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), roleId));
        }
        return employeeRoleMasterDbSvc.removeSysApisFromRoles(sysApiIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from EmployeeRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return employeeRoleMasterDbSvc.removeSysApisFromRoles(null, roleIds);
    }

    @Override
    public EmployeeRole toNewEntity(final EmployeeRoleMasterVO role) {
        final EmployeeRole newEntity = new EmployeeRole();
        assembleCommonAttrsOnInsert(newEntity, role);
        assembleSimpleRoleAttrs(newEntity, role);
        return newEntity;
    }

    @Override
    public EmployeeRole toUpdatableObj(final EmployeeRoleUpdatableObj updatableObj) {
        final EmployeeRole example = new EmployeeRole();
        assembleRoleUpdatableAttrs(example, updatableObj);
        return example;
    }
}
