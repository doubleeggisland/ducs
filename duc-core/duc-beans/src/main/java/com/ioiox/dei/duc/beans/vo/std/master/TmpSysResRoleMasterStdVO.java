package com.ioiox.dei.duc.beans.vo.std.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class TmpSysResRoleMasterStdVO
        extends BaseTmpRoleMasterStdVO {
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
    /**
     * 分配的系统资源权限
     */
    private List<Long> sysResIds;
}
