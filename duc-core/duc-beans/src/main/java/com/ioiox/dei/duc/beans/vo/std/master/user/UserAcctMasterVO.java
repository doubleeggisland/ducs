package com.ioiox.dei.duc.beans.vo.std.master.user;

import com.ioiox.dei.duc.beans.vo.std.master.UserMasterVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctMasterVO extends UserMasterVO {
    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 所属企业ID
     */
    private Long corpId;
}
