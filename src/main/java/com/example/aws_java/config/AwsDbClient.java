package com.example.aws_java.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsDbClient {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean

    @Profile("localToS3Mysql")
    public S3Client localToS3Mysql(@Value("${cloud.aws.credentials.access-key}") String accessKey,
                          @Value("${cloud.aws.credentials.secret-key}") String secretKey){
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    @Profile("awsEc2ToS3Mysql")
    public S3Client awsEC2ToS3Mysql(){
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .build();
    }

    @Bean
    @Profile("localToDynamoDB")
    public DynamoDbEnhancedClient localToDynamoDB(@Value("${cloud.aws.credentials.access-key}") String accessKey,
                                              @Value("${cloud.aws.credentials.secret-key}") String secretKey){
        var dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                )).build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    @Profile("awsEc2ToDynamoDB")
    public DynamoDbEnhancedClient awsEc2ToDynamoDB(){
        var dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.builder().build()).build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }


}
