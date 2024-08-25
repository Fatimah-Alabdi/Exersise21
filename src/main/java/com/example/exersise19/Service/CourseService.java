package com.example.exersise19.Service;

import com.example.exersise19.Api.ApiException;
import com.example.exersise19.Model.Course;
import com.example.exersise19.Model.Student;
import com.example.exersise19.Model.Teacher;
import com.example.exersise19.Repository.CourseRepository;
import com.example.exersise19.Repository.StudentRepository;
import com.example.exersise19.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course) {
        Course findCourse = courseRepository.findCourseById(id);
        if (findCourse == null) {
            throw new ApiException("Course not found");
        }
        findCourse.setName(course.getName());
        courseRepository.save(findCourse);
    }

    public void deleteCourse(Integer id) {
        Course findCourse = courseRepository.findCourseById(id);
        if (findCourse == null) {
            throw new ApiException("Course not found");
        }
        courseRepository.delete(findCourse);
    }

    public void assighnTeacherToCourse(Integer course_id, Integer teacher_id) {
        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw new ApiException("Course not found");
        }
        Teacher teacher1 = teacherRepository.findTeacherById(teacher_id);
        if (teacher1 == null) {
            throw new ApiException("Teacher not found");
        }
        course1.setTeacher(teacher1);
        courseRepository.save(course1);

    }
    public String getTeacherNameById(Integer course_id) {
        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw new ApiException("Course not found");
        }
       return course1.getTeacher().getName();



    }
    public Set<Student> getStudentsByCourseId(Integer course_id) {

        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw new ApiException("Course not found");
        }
        if (course1.getStudents().isEmpty()) {
            throw new ApiException("No students found");
        }
        return course1.getStudents();

    }
}