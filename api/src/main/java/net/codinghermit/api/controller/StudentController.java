package net.codinghermit.api.controller;

import net.codinghermit.api.
                           exception.StudentIdAlreadyExistException;
import net.codinghermit.api.
                           exception.StudentIdNotFoundException;
import net.codinghermit.api.model.Student;
import net.codinghermit.api.repo.StudentRepository;

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
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    // get all students
    @GetMapping("/students")
    @Cacheable("students")
    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    // empty cache
    @GetMapping("/sec/clear/students")
    @CacheEvict(value = "students", allEntries = true)
    public void emptyStudentsCache() {
        System.out.println("Emptying students cache!");
    }

    // create student rest API
    @Async("asyncExecutor")
    @PostMapping("/sec/students")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "students", allEntries = true)
    public Student createStudent(@RequestBody Student student)  {
        if(studentRepository.findById(student.getStudentId())==null) {
            int studentid = studentRepository.insert(student);
            System.out.println(studentid);

            // try {
            //     Thread.sleep(10000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            // System.out.println("I take 10 seconds to complete on a thread named: " + Thread.currentThread().getName());

            return studentRepository.findById(student.getStudentId());
        }else
        {
            throw new StudentIdAlreadyExistException();
        }

    }

    // get student by studentid rest api
    @GetMapping("/students/{studentid}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentid) {
        Student student = studentRepository.findById(studentid);
        if(student==null)
        {
            throw new StudentIdNotFoundException();
        }
        return ResponseEntity.ok(student);
    }

    // update student rest api
    @Async("asyncExecutor")
    @PutMapping("/sec/students/{studentid}")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "students", allEntries = true)
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentid,
                @RequestBody Student studentDetails) {
    if(studentRepository.update(new Student(studentid, studentDetails.getStudentName(), studentDetails.getEmailId(), studentDetails.getRole(), studentDetails.getPassword()))==0)
                {
                    throw new StudentIdNotFoundException();
                }

        return ResponseEntity.ok(studentRepository.findById(studentid));
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete student rest api
    @Async("asyncExecutor")
    @DeleteMapping("/sec/students/{studentid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "students", allEntries = true)
    public ResponseEntity<Map<String, Boolean>> deleteStudent
               (@PathVariable Long studentid) {
        studentRepository.deleteById(studentid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
