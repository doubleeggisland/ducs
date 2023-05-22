package com.ioiox.dei.duc.springboot.rest.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChgPwdReq {
    private String originPwd;
    private String newPwd;
}
