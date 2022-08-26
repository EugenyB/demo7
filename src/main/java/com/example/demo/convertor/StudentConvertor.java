package com.example.demo.convertor;

import com.example.demo.dto.StudentDto;
import com.example.demo.entities.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class StudentConvertor {
    public StudentDto studentToStudentDto(Student student) {
        return StudentDto.builder().id(student.getId()).name(student.getName())
                .birthday(student.getBirthday().format(DateTimeFormatter.ISO_DATE))
                .build();
    }

    public Student studentDtoToStudent(StudentDto s) {
        Student student = new Student();
        student.setId(s.getId());
        student.setName(s.getName());
        student.setBirthday(LocalDate.parse(s.getBirthday()));
        return student;
    }
}
