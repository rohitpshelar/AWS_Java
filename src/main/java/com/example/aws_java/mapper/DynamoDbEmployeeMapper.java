package com.example.aws_java.mapper;


import com.example.aws_java.dto.EmployeeDto;
import com.example.aws_java.entity.DynamoDbEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DynamoDbEmployeeMapper {

    DynamoDbEmployeeMapper INSTANCE = Mappers.getMapper( DynamoDbEmployeeMapper.class );

    EmployeeDto toDto(DynamoDbEmployee employee);

    //    @Mapping(target = "id", ignore = true)
    DynamoDbEmployee toEntity(EmployeeDto employeeDto);

    List<EmployeeDto> toListDto(List<DynamoDbEmployee> employee);

    List<DynamoDbEmployee> toListEntity(List<EmployeeDto> employeeDto);
}
