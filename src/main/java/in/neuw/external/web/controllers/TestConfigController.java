package in.neuw.external.web.controllers;

import in.neuw.external.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//@RefreshScope
@RestController
public class TestConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    public Map<String, String> getConfig() {
        return configService.getConfig();
    }

    @PostMapping("/refresh")
    public PropertySource<?> refreshConfig() {
        // refresh, calling actuator directly, we may still use actuator/refresh, it is same thing!
        return configService.updateConfig();
    }

}
