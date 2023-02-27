package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysResMasterStdVO extends MasterStdDataVO {
    private String code;
    private String name;
    private String type;
    private String status;
    private String memo;
    private String sysPrjModuleName;
    private String sysPrjModuleCode;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
}
