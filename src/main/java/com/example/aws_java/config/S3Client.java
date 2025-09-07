package com.example.aws_java.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class S3Client {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    @Profile("local")
    public software.amazon.awssdk.services.s3.S3Client S3Client(@Value("${cloud.aws.credentials.access-key}") String accessKey,
                                                                @Value("${cloud.aws.credentials.secret-key}") String secretKey){
        return software.amazon.awssdk.services.s3.S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    @Profile("aws")
    public software.amazon.awssdk.services.s3.S3Client S3ClientAws(){
        return software.amazon.awssdk.services.s3.S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
