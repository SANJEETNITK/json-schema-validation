package com.springboot.awss3example.serviceImpl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.springboot.awss3example.service.S3FileUploadService;
import com.springboot.awss3example.utils.Constants;
import com.springboot.awss3example.utils.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadService implements S3FileUploadService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    AmazonS3 s3Client;

    @Autowired
    FileUtils fileUtils;

    @Override
    public String uploadFileToS3(MultipartFile file) {
        File fileObj = fileUtils.convertMultiPartFileToFile(file);
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        PutObjectResult result = s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName + "\n" + result;
    }


    @Override
    public byte[] downloadFileFromS3(String fileName) {
        try {
            S3Object s3Object = s3Client.getObject(bucketName, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(inputStream);
        } catch (AmazonS3Exception exception) {
            exception.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public String deleteFileFromS3(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    @Override
    public URL uploadFileFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        String filename = UUID.randomUUID() + FilenameUtils.getName(String.valueOf(url));

        Path tempFilePath = Files.createTempFile(FilenameUtils.getBaseName(filename), "."+ FilenameUtils.getExtension(filename));
        File file = tempFilePath.toFile();

        org.apache.commons.io.FileUtils.copyURLToFile(url, file);
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, filename, file));
            return s3Client.getUrl(bucketName, filename);
        } finally {
            Files.delete(tempFilePath);
        }
    }

    @Override
    public URL generatePreSignedUrlForFileUpload(String fileName) {
        fileName = UUID.randomUUID() + fileName;

        Date expirationDate = new Date(System.currentTimeMillis() + Constants.PRESIGNED_URL_EXPIRATION_TIME_IN_MS);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expirationDate);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }

    @Override
    public URL getPreSignedUrl(String fileName) {
        Date expirationDate = new Date(System.currentTimeMillis() + Constants.PRESIGNED_URL_EXPIRATION_TIME_IN_MS);
        return s3Client.generatePresignedUrl(bucketName, fileName, expirationDate);
    }

}
