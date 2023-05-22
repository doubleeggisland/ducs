package com.ioiox.dei.duc.springboot.rest.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginReq {
    private String username;
    private String pwd;

    public LoginReq(final String username, final String pwd) {
        this.username = username;
        this.pwd = pwd;
    }
}
