package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.StdDataDelParam;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.model.RoleUpdatableObj;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;

import java.util.List;

public abstract class BaseRoleMasterStdDataSvc<
        T extends BaseRoleMasterStdVO,
        O extends RoleUpdatableObj,
        E extends Role>
        extends CommonRoleMasterStdDataSvc<T, O, E> {

    public Long save(final T role) {
        return null;
    }

    public boolean update(final T role) {
        return false;
    }

    public void delete(final StdDataDelParam delParam) {

    }

    protected int syncMenus(final List<Long> menuIds,
                             final List<Long> existingMenuIds,
                             final Long roleId,
                             final String operator) {
        return DeiGlobalConstant.ZERO;
    }

    protected int syncSysApis(final List<Long> sysApiMappingIds,
                               final List<Long> existingSysApiMappingIds,
                               final Long roleId,
                               final String operator) {
        return DeiGlobalConstant.ZERO;
    }

    protected void assignMenusToRole(final List<Long> menuIds, final Long roleId, final String operator) {

    }

    protected void removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator) {

    }

    protected void assignSysApisToRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {

    }

    protected void removeSysApisFromRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {

    }
}
