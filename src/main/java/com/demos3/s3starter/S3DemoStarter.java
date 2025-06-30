package com.demos3.s3starter;


import com.demos3.s3opertionServices.*;
import com.demos3.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PublicAccessBlockConfiguration;
import software.amazon.awssdk.services.s3.model.PutPublicAccessBlockRequest;

@Component
public class S3DemoStarter {

    private static final Logger log = LoggerFactory.getLogger(S3DemoStarter.class);
    private final CreateAWSS3buckets awsS3Create;
    private final CopyingFiles copyingFiles;
    private final DeleteFileFromBuckets deleteFileFromBuckets;
    private final DownloadFile downloadFile;
    private final InsertFileInBucket insertFileInBucket;
    private final ListOfBucketFiles listOfBucketFiles;
    private final S3Client s3Client;

    public S3DemoStarter(CreateAWSS3buckets awsS3Create, CopyingFiles copyingFiles, DeleteFileFromBuckets deleteFileFromBuckets, DownloadFile downloadFile, InsertFileInBucket insertFileInBucket, ListOfBucketFiles listOfBucketFiles, S3Client s3Client) {
        this.awsS3Create = awsS3Create;
        this.copyingFiles = copyingFiles;
        this.deleteFileFromBuckets = deleteFileFromBuckets;
        this.downloadFile = downloadFile;
        this.insertFileInBucket = insertFileInBucket;
        this.listOfBucketFiles = listOfBucketFiles;
        this.s3Client = s3Client;
    }

    public void blockPublicAccess(String bucket) {
        try {
            PutPublicAccessBlockRequest putPublicAccessBlockRequest = PutPublicAccessBlockRequest.builder()
                    .bucket(bucket)
                    .publicAccessBlockConfiguration(PublicAccessBlockConfiguration.builder()
                            .blockPublicAcls(true)
                            .blockPublicPolicy(true)
                            .ignorePublicAcls(true)
                            .restrictPublicBuckets(true)
                            .build())
                    .build();
           s3Client.putPublicAccessBlock(putPublicAccessBlockRequest);
        } catch (Exception ex) {
            log.error("Error blocking public access block", ex);
        }

    }

    public void callingAllOperation() {
        awsS3Create.createBucket(Constants.TRANSIENT_BUCKET_NAME);

        log.info("Performing all operations");

    }
}
