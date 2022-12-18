package net.codinghermit.api.controller;

import net.codinghermit.api.
                           exception.CourseIdAlreadyExistException;
import net.codinghermit.api.
                           exception.CourseIdNotFoundException;
import net.codinghermit.api.model.Course;
import net.codinghermit.api.repo.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    // get all courses
    @GetMapping("/courses")
    @Cacheable("courses")
    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    // empty cache
    @GetMapping("/sec/clear/courses")
    @CacheEvict(value = "courses", allEntries = true)
    public void emptyCoursesCache() {
        System.out.println("Emptying courses cache!");
    }

    // create course rest API
    @Async("asyncExecutor")
    @PostMapping("/sec/courses")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "courses", allEntries = true)
    public Course createCourse(@RequestBody Course course)  {
        if(courseRepository.findById(course.getCourseId())==null) {
            int courseId = courseRepository.insert(course);
            System.out.println(courseId);
            return courseRepository.findById(course.getCourseId());
        }else
        {
            throw new CourseIdAlreadyExistException();
        }

    }

    // get course by courseId rest api
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId);
        if(course==null)
        {
            throw new CourseIdNotFoundException();
        }
        return ResponseEntity.ok(course);
    }

    // update course rest api
    @Async("asyncExecutor")
    @PutMapping("/sec/courses/{courseId}")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "courses", allEntries = true)
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId,
                @RequestBody Course courseDetails) {
    if(courseRepository.update(new Course(courseId, courseDetails.getCourseName(), courseDetails.getId()))==0)
                {
                    throw new CourseIdNotFoundException();
                }

        return ResponseEntity.ok(courseRepository.findById(courseId));
    }

    // delete course rest api
    @Async("asyncExecutor")
    @DeleteMapping("/sec/courses/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "courses", allEntries = true)
    public ResponseEntity<Map<String, Boolean>> deleteCourse
               (@PathVariable Long courseId) {
        courseRepository.deleteById(courseId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
