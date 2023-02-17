package com.springboot.awss3example.service;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface S3BucketService {
    
    String createBucket(String bucketName);


    List<String> getBucketList();

    List<S3ObjectSummary> getBucketfiles(String bucketName);

    String hardDeleteBucket(String bucketName);

    String softDeleteBucket(String bucketName);
}
