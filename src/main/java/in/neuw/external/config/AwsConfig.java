package in.neuw.external.config;

import in.neuw.external.utils.S3Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

    @Bean
    public S3Client s3Client() {
        // can use other options of clients, based on other variants of AwsCredentialsProvider, and different region
        // for now internally it is ap-south-1 for my own interest(s)
        return S3Util.defaultClient();
    }

}
