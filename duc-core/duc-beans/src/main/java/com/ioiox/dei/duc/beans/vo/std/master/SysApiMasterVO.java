package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysApiMasterVO extends MasterStdDataVO {
    private String code;
    private String name;
    /**
     * 系统接口类型
     * @see com.ioiox.dei.duc.spring.core.model.DUCSysApiType
     */
    private String type;
    private String memo;
    private String url;
    private String httpMethod;
    private String sysPrjModuleName;
    private String sysPrjModuleCode;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
    private String status;
}
