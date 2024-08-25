package com.example.exersise19.Repository;

import com.example.exersise19.Model.Course;
import com.example.exersise19.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseById(int id);

}
