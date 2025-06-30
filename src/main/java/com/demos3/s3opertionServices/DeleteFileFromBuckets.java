package com.demos3.s3opertionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;


@Component
public class DeleteFileFromBuckets {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFileFromBuckets.class);
    private final S3Client s3Client;

    public DeleteFileFromBuckets(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void deleteFileFrombuckets(String buckets, String key) {
        try {
            DeleteObjectRequest deleteObjectsRequest = DeleteObjectRequest.builder().bucket(buckets).key(key).build();
           s3Client.deleteObject(deleteObjectsRequest);
            logger.info("Successfully deleted file from buckets");
        } catch (Exception ex) {
            logger.error("Failed to delete file from buckets", ex);
        }

    }
}
