package com.ioiox.dei.duc.beans.vo.std.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilegeMasterStdVO {
    private Long userId;
    private Long sysPrjId;
    private String accessCondition;
}
