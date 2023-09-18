package com.ioiox.dei.duc.beans.vo.std.slave.employee;

import com.ioiox.dei.duc.beans.vo.std.slave.UserSlaveVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeSlaveVO
        extends UserSlaveVO<EmployeeRoleSlaveVO, EmployeeSysResRoleSlaveVO, EmployeeTmpRoleSlaveVO, EmployeeTmpSysResRoleSlaveVO, EmployeeUserGrpSlaveVO> {
    /**
     * 姓名
     */
    private String realName;
    /**
     * 出生日期
     */
    private Long dateOfBirth;
    /**
     * 性别
     */
    private String gender;
}
