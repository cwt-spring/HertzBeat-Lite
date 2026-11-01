package com.cwtspring.hertzbeatlite.system.service;

import com.cwtspring.hertzbeatlite.system.dto.SystemInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoService {
    private final String name;
    private final String version;

    public SystemInfoService(
            @Value("${app.name}") String name,
            @Value("${app.version}") String version) {
        this.name = name;
        this.version = version;
    }
    public SystemInfoResponse systemInfo() {
        return new SystemInfoResponse(name, version);
    }
}
