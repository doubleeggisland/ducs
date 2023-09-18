package com.ioiox.dei.duc.beans.vo.std.master.employee;

import com.ioiox.dei.duc.beans.vo.std.master.UserMasterVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeMasterVO
        extends UserMasterVO {
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
