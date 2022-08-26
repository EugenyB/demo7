package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class StudentDto implements Serializable {
    private Integer id;
    private String name;
    private String birthday;
}
