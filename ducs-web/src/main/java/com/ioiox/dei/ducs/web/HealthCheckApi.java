package com.ioiox.dei.ducs.web;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.vo.DeiResponseData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApi {

    @GetMapping(path = "/ducs/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeiResponseData<String> info() {
        return new DeiResponseData.Builder<String>()
                .code(DeiResponseData.SUCCESS)
                .success(DeiGlobalConstant.TRUE_STR)
                .data("1.0.0-SNAPSHOT")
                .build();
    }
}
