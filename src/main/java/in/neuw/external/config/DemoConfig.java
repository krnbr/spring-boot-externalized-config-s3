package in.neuw.external.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DemoConfig {

    private Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    @Autowired
    ConfigurableEnvironment environment;

    @Bean
    @RefreshScope
    public Map<String, String> commonConfig() {
        logger.info("common config (re)initialized");
        Map<String, String> map = new HashMap();
        map.put("count", environment.getProperty("config.count"));
        map.put("name", environment.getProperty("config.name"));
        map.put("message", environment.getProperty("config.message"));
        return map;
    }

}
