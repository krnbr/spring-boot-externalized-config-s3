package in.neuw.external.listener;

import in.neuw.external.utils.PropertyLoader;
import in.neuw.external.utils.S3Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

public class AppEnvPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private Logger logger = LoggerFactory.getLogger(AppEnvPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        logger.info("preparing the environment config");
        ConfigurableEnvironment environment = event.getEnvironment();
        PropertyLoader.loadPropertiesFromS3(S3Util.defaultClient(), environment);
        logger.info("prepared the environment config");
    }

}
