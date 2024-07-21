package in.neuw.external.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Properties;

import static in.neuw.external.utils.Constants.*;

public class PropertyLoader {

    private static Logger logger = LoggerFactory.getLogger(PropertyLoader.class);

    public static void loadPropertiesFromS3(final S3Client s3, final ConfigurableEnvironment environment) {
        logger.info("loading properties from S3");
        var config = S3Util.getS3ObjectByKey(s3, CONFIG_BUCKET_NAME, CONFIG_BUCKET_OBJECT_KEY);
        Properties props = new Properties();
        props.put("config.count", config.getCount());
        props.put("config.name", config.getName());
        props.put("config.message", config.getMessage());
        environment.getPropertySources().addFirst(new PropertiesPropertySource(CUSTOM_CONFIG, props));
        logger.info("loaded properties from S3");
    }

}
