package com.example.aws_java.repository;

import com.example.aws_java.entity.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("!localToDynamoDB & !awsEc2ToDynamoDB")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
