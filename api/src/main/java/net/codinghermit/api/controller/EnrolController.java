package net.codinghermit.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.codinghermit.api.
                           exception.EnrolIdAlreadyExistException;
import net.codinghermit.api.
                           exception.EnrolIdNotFoundException;
import net.codinghermit.api.model.Enrol;
import net.codinghermit.api.repo.EnrolRepository;

// @CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class EnrolController {
    // private EnrolService enrolService;

    @Autowired
    private EnrolRepository enrolRepository;

    // get all enrollments
    @GetMapping("/enrollments")
    @Cacheable("enrollments")
    public List<Enrol> getAllEnrols()
    {
        return enrolRepository.findAll();
    }

    // empty cache
    @GetMapping("/sec/clear/enrollments")
    @CacheEvict(value = "enrollments", allEntries = true)
    // @Scheduled(fixedRateString = "${caching.enrolListTTL}")
    public void emptyEnrolsCache() {
        System.out.println("Emptying enrols cache!");
    }

    // create enrollment
    @Async("asyncExecutor")
    @PostMapping("/sec/enrollments")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "enrollments", allEntries = true)
    public Enrol createEnrol(@RequestBody Enrol enrol)  {
        if(enrolRepository.findById(enrol.getCourseId(), enrol.getStudentId())==null) {
            int enrolid = enrolRepository.insert(enrol);
            System.out.println(enrolid);
            return enrolRepository.findById(enrol.getCourseId(), enrol.getStudentId());
        }else
        {
            throw new EnrolIdAlreadyExistException();
        }

    }

    // get enrollment by enrolid
    @GetMapping("/enrollments/{courseid}/{studentid}")
    public ResponseEntity<Enrol> getEnrolById(@PathVariable Long courseid, @PathVariable Long studentid) {
        Enrol enrol = enrolRepository.findById(courseid, studentid);
        if(enrol==null)
        {
            throw new EnrolIdNotFoundException();
        }
        return ResponseEntity.ok(enrol);
    }

    // update enrol rest api
    // @Async("asyncExecutor")
    // @PutMapping("/enrollments/{enrolid}")
    // @ResponseStatus(HttpStatus.CREATED)
    // @CacheEvict(value = "enrollments", allEntries = true)
    // public ResponseEntity<Enrol> updateEnrol(@PathVariable Long enrolid,
    //             @RequestBody Enrol enrolDetails) {
    // if(enrolRepository.update(new Enrol(enrolid, enrolDetails.getEnrolName(), enrolDetails.getEmailId()))==0)
    //             {
    //                 throw new EnrolIdNotFoundException();
    //             }

    //     return ResponseEntity.ok(enrolRepository.findById(enrolid));
    // }

    // delete enrollment
    @Async("asyncExecutor")
    @DeleteMapping("/sec/enrollments/{enrolid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "enrollments", allEntries = true)
    public ResponseEntity<Map<String, Boolean>> deleteEnrol
               (@PathVariable Long enrolid) {
        enrolRepository.deleteById(enrolid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
