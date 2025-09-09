package com.example.aws_java.mapper;


import com.example.aws_java.dto.EmployeeDto;
import com.example.aws_java.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    EmployeeDto toDto(Employee employee);

//    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeDto employeeDto);

    List<EmployeeDto> toListDto(List<Employee> employee);

    List<Employee> toListEntity(List<EmployeeDto> employeeDto);
}
