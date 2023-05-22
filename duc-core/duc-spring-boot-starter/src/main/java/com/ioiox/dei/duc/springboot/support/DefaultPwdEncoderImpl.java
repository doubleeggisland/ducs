package com.ioiox.dei.duc.springboot.support;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DefaultPwdEncoderImpl implements PwdEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DefaultPwdEncoderImpl(final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 加密密码
     * @param rawPwd 明文密码
     * @return 加密后的密码
     */
    public String encodePwd(final String rawPwd) {
        return bCryptPasswordEncoder.encode(rawPwd);
    }

    /**
     * 校验密码是否匹配
     * @param rawPwd 明文密码
     * @param encodedPwd 加密后的密码
     * @return true: 匹配, false: 不匹配
     */
    public boolean pwdMatches(final String rawPwd, final String encodedPwd) {
        return bCryptPasswordEncoder.matches(rawPwd, encodedPwd);
    }
}
