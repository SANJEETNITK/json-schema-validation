package com.springboot.awss3example.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.springboot.awss3example.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3BucketServiceImpl implements S3BucketService {

    @Autowired
    AmazonS3 amazonS3;

    @Override
    public String createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            // bucket is created in the region specified in the client.
            amazonS3.createBucket(new CreateBucketRequest(bucketName));
            // Verify that the bucket was created by retrieving it and checking its location.
            return "Bucket Created \nBucket Name:-" + bucketName +"\nregion:-"
                    + amazonS3.getBucketLocation(new GetBucketLocationRequest(bucketName));
        }
        return "Bucket Already Exist";
    }

    @Override
    public List<String> getBucketList() {
        return amazonS3.listBuckets().stream().map(Bucket::getName).collect(Collectors.toList());
    }

    @Override
    public List<S3ObjectSummary> getBucketfiles(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            return null;
        }

        return new ArrayList<>(amazonS3.listObjectsV2(bucketName).getObjectSummaries());
    }

    @Override
    public String hardDeleteBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            return "No Bucket Found";
        }
        ListObjectsV2Result results = amazonS3.listObjectsV2(bucketName);
        for (S3ObjectSummary s3ObjectSummary : results.getObjectSummaries()) {
            amazonS3.deleteObject(bucketName, s3ObjectSummary.getKey());
        }
        return "Bucket Deleted Successfully";
    }

    @Override
    public String softDeleteBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            return "No Bucket Found";
        }
        if (amazonS3.listObjectsV2(bucketName).isTruncated()) {
            amazonS3.deleteBucket(bucketName);
            return "Bucket Deleted Successfully";
        }
        return "Bucket have some files";
    }
}
