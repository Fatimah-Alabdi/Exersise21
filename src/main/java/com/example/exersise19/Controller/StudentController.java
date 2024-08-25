package com.example.exersise19.Controller;

import com.example.exersise19.Api.ApiResponse;
import com.example.exersise19.Model.Student;
import com.example.exersise19.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/get")
    public ResponseEntity getAllStudent() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id,@Valid@RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));
    }
    @PutMapping("/{student_id}/assghin/{course_id}")
    public ResponseEntity assighnStudentToCourse(@PathVariable Integer student_id, @PathVariable Integer course_id) {
        studentService.assighnStudentToCourse(student_id, course_id);
        return ResponseEntity.status(200).body(new ApiResponse("student assighned successfully"));
    }
    @PutMapping("/chnage/{student_id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer student_id, @PathVariable String major) {
        studentService.changeStudentMajor(student_id, major);
        return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
    }
}
