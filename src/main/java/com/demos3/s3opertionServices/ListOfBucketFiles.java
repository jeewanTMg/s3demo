package com.demos3.s3opertionServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import java.util.ArrayList;
import java.util.List;


@Component
public class ListOfBucketFiles {
    private static final Logger logger = LoggerFactory.getLogger(ListOfBucketFiles.class);
    private final S3Client s3Client;

    public ListOfBucketFiles(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> listOfFile(String bucket) {
        List<String> keys = new ArrayList();

        try {
            ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder().bucket(bucket).build();
            ListObjectsResponse listObjectsResponse = this.s3Client.listObjects(listObjectsRequest);
            listObjectsResponse.contents().forEach((object) -> keys.add(object.key()));
        } catch (Exception ex) {
            logger.error("Error listing files", ex);
        }

        return keys;
    }
}
