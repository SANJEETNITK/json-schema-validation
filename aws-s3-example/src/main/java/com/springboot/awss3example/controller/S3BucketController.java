package com.springboot.awss3example.controller;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.springboot.awss3example.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/s3/bucket")
public class S3BucketController {

    @Autowired
    S3BucketService s3BucketService;

    @PostMapping("/create/{bucket-name}")
    public String createBucket(@PathVariable("bucket-name") String bucketName) {
        return s3BucketService.createBucket(bucketName);
    }

    @GetMapping(value = "/list")
    public List<String> getBucketList() {
        return s3BucketService.getBucketList();
    }

    @GetMapping(value = "/files/{bucketName}")
    public List<S3ObjectSummary> getBucketfiles(@PathVariable String bucketName) {
        return s3BucketService.getBucketfiles(bucketName);
    }

    @DeleteMapping(value = "/delete/hard/{bucketName}")
    public String hardDeleteBucket(@PathVariable String bucketName) {
        return s3BucketService.hardDeleteBucket(bucketName);
    }

    @DeleteMapping(value = "/delete/{bucketName}")
    public String softDeleteBucket(@PathVariable String bucketName) {
        return s3BucketService.softDeleteBucket(bucketName);
    }

}
