package com.cwtspring.hertzbeatlite.system.controller;

import com.cwtspring.hertzbeatlite.system.dto.SystemInfoResponse;
import com.cwtspring.hertzbeatlite.system.service.SystemInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SystemInfoController {
    private final SystemInfoService systemInfoService;

    public SystemInfoController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @GetMapping("/system/info")
    public SystemInfoResponse info() {
        return systemInfoService.systemInfo();
    }
}
