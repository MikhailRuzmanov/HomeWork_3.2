package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        if(student==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }
    @GetMapping
    public  ResponseEntity<Collection<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student){
        Student foundStudent = studentService.editStudent(student);
        if(foundStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);

    }
    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }
    @GetMapping("age")
    public Collection<Student> getAgeStudents(@PathVariable int age){
        return studentService.getAgeStudent(age);
    }

}
