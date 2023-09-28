package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeUserGrp;
import com.ioiox.dei.duc.beans.entity.UserGrp;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUserGrpDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUserGrpUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUserGrpUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeUserGrpMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeUserGrpSlaveVO;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeUserGrpSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeUserGrpMasterStdDataSvc")
public class EmployeeUserGrpMasterStdDataSvcImpl
        extends BaseUserGrpMasterStdDataSvc<EmployeeUserGrpMasterVO, EmployeeUserGrpUpdatableObj, EmployeeUserGrpUpdateCtx, EmployeeUserGrpDelParam, EmployeeUserGrpSlaveVO, EmployeeUserGrp>
        implements EmployeeUserGrpMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(EmployeeUserGrpMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("employeeUserGrpMasterDbSvc")
    private EmployeeUserGrpMasterDbSvc employeeUserGrpMasterDbSvc;

    @Autowired
    @Qualifier("employeeUserGrpSlaveStdDataSvc")
    private EmployeeUserGrpSlaveStdDataSvc employeeUserGrpSlaveStdDataSvc;

    private final EmployeeUserGrpUpdatableAttrsAnalyser updatableAttrsAnalyser = new EmployeeUserGrpUpdatableAttrsAnalyser();

    @Override
    protected EmployeeUserGrpSlaveVO getExistingUserGrp(final Long id) {
        return employeeUserGrpSlaveStdDataSvc.queryByPk(id,
                new UserGrpQueryCfg.Builder()
                        .needRoles(DeiGlobalConstant.FLAG_YES)
                        .roleQueryCfg(new RoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysResRoles(DeiGlobalConstant.FLAG_YES)
                        .sysResRoleQueryCfg(new SysResRoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                UserGrp.ShowColumn.CODE.getCode(), UserGrp.ShowColumn.NAME.getCode(),
                                UserGrp.ShowColumn.MEMO.getCode(), UserGrp.ShowColumn.STATUS.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<EmployeeUserGrpSlaveVO> queryExistingUserGrps(final EmployeeUserGrpDelParam delParam) {
        final EmployeeUserGrpQueryParam queryParam = new EmployeeUserGrpQueryParam.Builder()
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return employeeUserGrpSlaveStdDataSvc.queryByParam(queryParam,
                new UserGrpQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected int assignRolesToUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign roles to EmployeeUserGrp =====> roleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(roleIds), userGrpId));
        }
        return employeeUserGrpMasterDbSvc.assignRolesToUserGrp(roleIds, userGrpId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeRolesFromUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from EmployeeUserGrp =====> roleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(roleIds), userGrpId));
        }
        return employeeUserGrpMasterDbSvc.removeRolesFromUserGrps(roleIds, Collections.singletonList(userGrpId));
    }

    @Override
    protected int removeRolesFromUserGrps(final List<Long> userGrpIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from EmployeeUserGrps ======> userGrpIds: %s", JsonUtil.toJsonStr(userGrpIds)));
        }
        return employeeUserGrpMasterDbSvc.removeRolesFromUserGrps(null, userGrpIds);
    }

    @Override
    protected int assignSysResRolesToUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResRoles to EmployeeUserGrp =====> sysResRoleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userGrpId));
        }
        return employeeUserGrpMasterDbSvc.assignSysResRolesToUserGrp(sysResRoleIds, userGrpId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResRolesFromUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from EmployeeUserGrp =====> sysResRoleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userGrpId));
        }
        return employeeUserGrpMasterDbSvc.removeSysResRolesFromUserGrps(sysResRoleIds, Collections.singletonList(userGrpId));
    }

    @Override
    protected int removeSysResRolesFromUserGrps(final List<Long> userGrpIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from EmployeeUserGrps ======> userGrpIds: %s", JsonUtil.toJsonStr(userGrpIds)));
        }
        return employeeUserGrpMasterDbSvc.removeSysResRolesFromUserGrps(null, userGrpIds);
    }

    @Override
    protected EmployeeUserGrpUpdateCtx getUpdateContext(final EmployeeUserGrpMasterVO userGrp,
                                                        final EmployeeUserGrpSlaveVO existingUserGrp) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(userGrp, existingUserGrp);
    }

    @Override
    protected void doSave(final EmployeeUserGrp newEntity) {
        employeeUserGrpMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final EmployeeUserGrp example) {
        return employeeUserGrpMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeUserGrpMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public EmployeeUserGrp toNewEntity(final EmployeeUserGrpMasterVO userGrp) {
        final EmployeeUserGrp newEntity = new EmployeeUserGrp();
        assembleCommonAttrsOnInsert(newEntity, userGrp);
        assembleCommonAttrs(newEntity, userGrp);
        return newEntity;
    }

    @Override
    public EmployeeUserGrp toUpdatableObj(final EmployeeUserGrpUpdatableObj updatableObj) {
        final EmployeeUserGrp example = new EmployeeUserGrp();
        assembleCommonUpdatableAttrs(example, updatableObj);
        return example;
    }
}
