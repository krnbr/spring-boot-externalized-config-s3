package in.neuw.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S3BackedConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(S3BackedConfigApplication.class, args);
    }

}
