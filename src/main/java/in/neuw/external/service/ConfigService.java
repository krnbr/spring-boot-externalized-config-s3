package in.neuw.external.service;

import in.neuw.external.utils.PropertyLoader;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.util.Map;

import static in.neuw.external.utils.Constants.CUSTOM_CONFIG;

@Service
public class ConfigService {

    private final ConfigurableEnvironment environment;

    private final S3Client s3Client;

    private final RefreshEndpoint refreshEndpoint;

    private final Map<String, String> commonConfig;

    public ConfigService(final ConfigurableEnvironment environment,
                         final S3Client s3Client,
                         final RefreshEndpoint refreshEndpoint,
                         final Map<String, String> commonConfig) {
        this.environment = environment;
        this.s3Client = s3Client;
        this.refreshEndpoint = refreshEndpoint;
        this.commonConfig = commonConfig;
    }

    public Map<String, String> getConfig() {
        return commonConfig;
    }

    public PropertySource<?> updateConfig() {
        PropertyLoader.loadPropertiesFromS3(s3Client, environment);
        refreshEndpoint.refresh();
        System.out.println(environment.getPropertySources().get(CUSTOM_CONFIG));
        return environment.getPropertySources().get(CUSTOM_CONFIG);
    }

}
