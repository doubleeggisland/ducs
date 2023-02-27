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
     * 分配的系统API权限
     */
    private List<Long> sysApiIds;
}
