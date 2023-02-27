package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMappingMasterStdVO extends MasterStdDataVO {
    private Long menuId;
    private Long sysApiId;
    private String interactForm;
}
