package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysPrjMasterStdVO extends MasterStdDataVO {
    private String code;
    private String name;
    /**
     * 项目状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    private String memo;
}
