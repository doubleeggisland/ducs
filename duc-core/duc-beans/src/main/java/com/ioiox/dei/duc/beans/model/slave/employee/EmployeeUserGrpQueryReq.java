package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUserGrpQueryReq {
    private EmployeeUserGrpQueryParam queryParam;
    private UserGrpQueryCfg queryCfg;

    public EmployeeUserGrpQueryReq(final EmployeeUserGrpQueryParam queryParam,
                                   final UserGrpQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
