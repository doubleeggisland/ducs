package com.ioiox.dei.duc.beans.vo.std.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class TmpRoleMasterStdVO extends BaseTmpRoleMasterStdVO {
    /**
     * 分配的菜单权限
     */
    private List<Long> menuIds;
    /**
     * 分配的菜单相关的系统接口权限(菜单与系统接口映射ID)
     */
    private List<Long> sysApiMappingIds;
    /**
     * 分配的系统接口权限
     */
    private List<Long> sysApiIds;
}
