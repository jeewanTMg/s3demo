package com.demos3.s3opertionServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;


@Component
public class CreateAWSS3buckets {

    private static final Logger logger = LoggerFactory.getLogger(CreateAWSS3buckets.class);
    private final S3Client s3Client;

    public CreateAWSS3buckets(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void createBucket(String bucketName) {
        try {
            boolean bucketExists = s3Client.listBuckets().buckets()
                    .stream()
                    .anyMatch(b -> b.name().equals(bucketName));

            if (!bucketExists) {
                CreateBucketRequest request = CreateBucketRequest.builder().bucket(bucketName).build();
                s3Client.createBucket(request);
            }

            logger.info("Bucket created successfully: {}", bucketName);
        } catch (Exception e) {
            logger.error("Error creating bucket: {}", bucketName, e);
        }

    }
}
