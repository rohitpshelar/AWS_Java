package com.example.aws_java.service;


import com.example.aws_java.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAll();

    EmployeeDto getById(Long id);

    EmployeeDto update(Long id, EmployeeDto employeeDto);

    void delete(Long id);
}
