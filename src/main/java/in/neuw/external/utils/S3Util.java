package in.neuw.external.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.neuw.external.models.CommonConfig;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.io.InputStream;

public class S3Util {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static S3Client defaultClient() {
        // change the profile name as per your setup, can be based on environment variable
        return s3Client("default");
    }

    public static S3Client s3Client(final String awsProfile) {
        return S3Client
                .builder()
                .credentialsProvider(ProfileCredentialsProvider.create(awsProfile))
                .region(Region.AP_SOUTH_1)
                .build();
    }

    public static S3Client s3Client(final String awsProfile, Region region) {
        return S3Client
                .builder()
                .credentialsProvider(ProfileCredentialsProvider.create(awsProfile))
                .region(region)
                .build();
    }

    public static S3Client s3Client() {
        // without any predefined credential provider
        return S3Client
                .builder()
                .region(Region.AP_SOUTH_1)
                .build();
    }

    public static S3Client s3ClientBasedOnEnv() {
        // AP SOUTH 1 = mumbai
        return S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.AP_SOUTH_1)
                .build();
    }

    public static CommonConfig getS3ObjectByKey(final S3Client s3,
                                                final String bucketName,
                                                final String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(key).build();
        ResponseInputStream<?> s3ObjectInputStream = s3.getObject(getObjectRequest);
        try (InputStream inputStream = s3ObjectInputStream) {
            CommonConfig config = objectMapper.readValue(inputStream, CommonConfig.class);
            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
