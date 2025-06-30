package com.demos3;

import com.demos3.s3starter.S3DemoStarter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class S3Start implements CommandLineRunner {

    private final S3DemoStarter awsS3Demo;

    public S3Start(S3DemoStarter awsS3Demo) {
        this.awsS3Demo = awsS3Demo;
    }

    @Override
    public void run(String... args) throws Exception {
        awsS3Demo.callingAllOperation();
    }
}
