package com.ioiox.dei.duc.springboot.autoconfig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DUCSysSettings {
    private String loginUrl;
    private String chgPwdUrl;
    private String resetPwdUrl;
    private String renewAccessTokenUrl;
    private String getUserPortfolioUrl;
    private String apiRootPath;
    private String [] skipUrls;
}
