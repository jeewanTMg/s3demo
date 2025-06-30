package com.demos3.s3opertionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import java.net.URLEncoder;

@Component
public class CopyingFiles {
    private static final Logger logger = LoggerFactory.getLogger(CopyingFiles.class);
    private final S3Client s3Client;

    public CopyingFiles(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void copyingFiles(String sourceBucket, String sourceKey, String destinationBucket, String destinationKey) {
        try {
            String encodedURL = URLEncoder.encode(sourceBucket + "/" + sourceKey, "UTF-8");
            CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                    .copySource(encodedURL)
                    .destinationBucket(destinationBucket)
                    .destinationKey(destinationKey)
                    .build();
            s3Client.copyObject(copyObjectRequest);
            logger.info("Successfully copied files");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }
}
