package com.demos3.s3opertionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.nio.file.Paths;


@Component
public class InsertFileInBucket {
    private static final Logger logger = LoggerFactory.getLogger(InsertFileInBucket.class);
    private final S3Client s3Client;

    public InsertFileInBucket(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadfile(String bucket, String localFile, String localDirectory, String key) {
        try {
            PutObjectRequest putObjectRequest =PutObjectRequest.builder().bucket(bucket).key(key).build();
            this.s3Client.putObject(putObjectRequest, Paths.get(localDirectory, localFile));
        } catch (Exception ex) {
            logger.error("Error uploading file", ex);
        }

    }
}
