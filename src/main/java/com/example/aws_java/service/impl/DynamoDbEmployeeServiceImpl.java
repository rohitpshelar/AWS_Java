package com.example.aws_java.service.impl;


import com.example.aws_java.dto.EmployeeDto;
import com.example.aws_java.mapper.DynamoDbEmployeeMapper;
import com.example.aws_java.repository.DynamoDbEmployeeRepository;
import com.example.aws_java.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Profile({"localToDynamoDB", "awsEc2ToDynamoDB"})
public class DynamoDbEmployeeServiceImpl implements EmployeeService {

    private DynamoDbEmployeeRepository dynamoDbEmployeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        var employee = DynamoDbEmployeeMapper.INSTANCE.toEntity(employeeDto);
        var e2 = dynamoDbEmployeeRepository.save(employee);
        return DynamoDbEmployeeMapper.INSTANCE.toDto(e2);
    }

    @Override
    public List<EmployeeDto> getAll() {
        return DynamoDbEmployeeMapper.INSTANCE.toListDto(dynamoDbEmployeeRepository.findAll());
    }

    @Override
    public EmployeeDto getById(Long id) {
        return DynamoDbEmployeeMapper.INSTANCE.toDto(dynamoDbEmployeeRepository.findById(id));
//        .orElseThrow(()->
//                new DataAccessResourceFailureException("Employee don't exist with given Id : " + id)));
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto employeeDto) {

        var employee  = dynamoDbEmployeeRepository.findById(id);
//        .orElseThrow(()->
//
//                new DataAccessResourceFailureException("Employee don't exist with given Id : " + id));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        dynamoDbEmployeeRepository.save(employee);
        return DynamoDbEmployeeMapper.INSTANCE.toDto(employee);
    }

    @Override
    public void delete(Long id) {
        dynamoDbEmployeeRepository.deleteById(id);
    }
}
