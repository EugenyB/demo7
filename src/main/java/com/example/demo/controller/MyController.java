package com.example.demo.controller;

import com.example.demo.convertor.StudentConvertor;
import com.example.demo.dto.StudentDto;
import com.example.demo.entities.Student;
import com.example.demo.repo.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MyController {

    private StudentRepository studentRepository;

    private StudentConvertor studentConvertor;

    @PostMapping("add")
    public String addStudent(@RequestParam String name, @RequestParam String birthday) {
        Student s = studentConvertor.studentDtoToStudent(StudentDto.builder().name(name).birthday(birthday).build());
        studentRepository.save(s);
        return "redirect:/students";
    }

    @GetMapping("students")
    public String showStudents(Model model) {
        List<StudentDto> list = studentRepository.findAll().stream().map(studentConvertor::studentToStudentDto).toList();
        model.addAttribute("students", list);
        return "students";
    }

    @GetMapping("edit_student")
    public String showEditStudentForm(@RequestParam int id, Model model) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", studentConvertor.studentToStudentDto(optionalStudent.get()));
            return "edit_student";
        } else {
            model.addAttribute("message", "Student with " + id + " is not exist");
            return "error";
        }
    }

    @PostMapping("update_student")
    public String editStudent(@RequestParam int id, StudentDto s) {
        Student student = studentRepository.findById(id).get();
        Student st = studentConvertor.studentDtoToStudent(s);
        student.setName(st.getName());
        student.setBirthday(st.getBirthday());

        studentRepository.save(student);

        return "redirect:/students";
    }

    @GetMapping("delete_student")
    public String deleteStudent(@RequestParam int id) {
        studentRepository.deleteById(id);

        return "redirect:/students";
    }
}
