package in.neuw.external.utils;

public interface Constants {

    // all these properties can be better referred from the environment variables, instead of hard coded constants

    // the bucket name for the config
    String CONFIG_BUCKET_NAME = "application-configs";
    // the object name in the bucket
    String CONFIG_BUCKET_OBJECT_KEY = "common-config.json";
    // the name of the property source
    String CUSTOM_CONFIG = "custom-config";

}
