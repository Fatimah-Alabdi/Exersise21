package com.example.exersise19.Service;

import com.example.exersise19.Api.ApiException;
import com.example.exersise19.Model.Course;
import com.example.exersise19.Model.Student;
import com.example.exersise19.Repository.CourseRepository;
import com.example.exersise19.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public void addStudent(Student student) {
        studentRepository.save(student);

    }
    public void updateStudent(Integer id,Student student) {
        Student student1 = studentRepository.findStudentById(id);
        if (student1 == null) {
            throw new ApiException("Student not found");
        }
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setMajor(student.getMajor());
        studentRepository.save(student1);

    }
    public void deleteStudent(Integer id) {
        Student student1 = studentRepository.findStudentById(id);
        if (student1 == null) {
            throw new ApiException("Student not found");
        }
        studentRepository.delete(student1);
    }
    public void assighnStudentToCourse(Integer studentId, Integer courseId) {
        Student student1 = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);
        if (student1 == null||course==null) {
            throw new ApiException("Student and course not found");
        }
        student1.getCourses().add(course);
        course.getStudents().add(student1);
        studentRepository.save(student1);
        courseRepository.save(course);
    }
    public void changeStudentMajor(Integer Id, String major) {
        Student student1 = studentRepository.findStudentById(Id);
        if (student1 == null) {
            throw new ApiException("Student not found");
        }
        student1.setMajor(major);
        student1.getCourses().clear();
        studentRepository.save(student1);
    }
}
