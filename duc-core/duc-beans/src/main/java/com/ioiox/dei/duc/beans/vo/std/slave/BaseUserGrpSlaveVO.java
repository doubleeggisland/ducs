package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseUserGrpSlaveVO extends SlaveStdDataVO {
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
