package com.ioiox.dei.duc.beans.vo.std.master.tenant;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantMasterVO extends MasterStdDataVO {
    /**
     * 租户编号
     */
    private String code;
    /**
     * 租户名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 租户状态
     * @see com.ioiox.dei.core.constant.DeiStatus
     */
    private String status;
    /**
     * 租户层级
     */
    private Integer lvl;
    /**
     * 父租户主键ID
     */
    private Long pid;
    /**
     * 归属的顶级租户ID
     */
    private Long rid;
}
