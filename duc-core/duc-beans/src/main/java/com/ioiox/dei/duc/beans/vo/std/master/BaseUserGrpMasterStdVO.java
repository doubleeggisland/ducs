package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseUserGrpMasterStdVO extends MasterStdDataVO {
    /**
     * 用户组编号
     */
    private String code;
    /**
     * 用户组名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 用户组状态
     */
    private String status;
}
