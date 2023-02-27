package com.ioiox.dei.duc.beans.vo.std.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserGrpMasterStdVO {
    private List<Long> roleIds;
    private List<Long> sysResRoleIds;
}
