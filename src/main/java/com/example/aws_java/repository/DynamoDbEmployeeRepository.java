package com.example.aws_java.repository;

import com.example.aws_java.entity.DynamoDbEmployee;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile({"localToDynamoDB", "awsEc2ToDynamoDB"})
public class DynamoDbEmployeeRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<DynamoDbEmployee> employeeDynamoDbTable;

    @PostConstruct
    public void init() {
        employeeDynamoDbTable = dynamoDbEnhancedClient.table("employee", TableSchema.fromBean(DynamoDbEmployee.class));
    }

    public DynamoDbEmployee save(DynamoDbEmployee product) {
        employeeDynamoDbTable.putItem(product);
        return product;
    }

    public DynamoDbEmployee findById(Long id) {
        return employeeDynamoDbTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public List<DynamoDbEmployee> findAll() {
        List<DynamoDbEmployee> products = new ArrayList<>();
        employeeDynamoDbTable.scan().items().forEach(products::add);
        return products;
    }

    public void deleteById(Long id) {
        employeeDynamoDbTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }


}
