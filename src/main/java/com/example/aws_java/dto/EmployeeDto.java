package com.example.aws_java.dto;

import lombok.*;

@Data
@Getter
@Setter
public class EmployeeDto {

    Long id;
    String firstName;
    String lastName;
    String email;
}
