package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.SlaveStdDataVO;
import com.ioiox.dei.duc.spring.core.model.DUCRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseRoleSlaveVO extends SlaveStdDataVO {
    /**
     * 角色编号
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型
     * @see DUCRoleType
     */
    private String type;
    /**
     * 状态
     * @see com.ioiox.dei.core.constant.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
}
