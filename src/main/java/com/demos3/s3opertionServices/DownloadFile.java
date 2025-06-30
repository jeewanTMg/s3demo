package com.demos3.s3opertionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import java.nio.file.Paths;


@Component
public class DownloadFile {

    private static final Logger logger = LoggerFactory.getLogger(DownloadFile.class);
    private final S3Client s3Client;

    public DownloadFile(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void downloadFile(String bucket, String localfile, String localDirectory, String key) {
        try {
            GetObjectRequest request = GetObjectRequest.builder().bucket(bucket).key(key).build();
            s3Client.getObject(request, Paths.get(localDirectory, localfile));
        } catch (Exception ex) {
            logger.error("Error downloading file", ex);
        }

    }
}
