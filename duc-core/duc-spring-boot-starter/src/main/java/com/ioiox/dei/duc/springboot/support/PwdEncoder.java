package com.ioiox.dei.duc.springboot.support;

public interface PwdEncoder {

    String encodePwd(final String rawPwd);
    boolean pwdMatches(final String rawPwd, final String encodedPwd);
}
