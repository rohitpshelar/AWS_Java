package com.example.aws_java.entity;


import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
@Builder
public class DynamoDbEmployee {

    Long id;

    String firstName;
    String lastName;
    String email;

    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }
}
