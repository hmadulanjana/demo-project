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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.codinghermit.api.
                           exception.UserIdAlreadyExistException;
import net.codinghermit.api.
                           exception.UserIdNotFoundException;
import net.codinghermit.api.model.User;
import net.codinghermit.api.repo.UserRepository;

// @CrossOrigin(origins = "*", allowedHeaders = "*")
// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class UserController {
    // private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // login
    @Async("asyncExecutor")
    @PostMapping("/sec/login")
    @ResponseStatus(HttpStatus.OK)
    public void login()
    {
        return;
    }


    // get all users
    @GetMapping("/users")
    @Cacheable("users")
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    // empty cache
    @GetMapping("/sec/clear/users")
    @CacheEvict(value = "users", allEntries = true)
    // @Scheduled(fixedRateString = "${caching.userListTTL}")
    public void emptyUsersCache() {
        System.out.println("Emptying users cache!");
    }

    // create user rest API
    @Async("asyncExecutor")
    @PostMapping("/sec/users")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "users", allEntries = true)
    public User createUser(@RequestBody User user)  {
        if(userRepository.findById(user.getId())==null) {
            int userid = userRepository.insert(user);
            System.out.println(userid);
            return userRepository.findById(user.getId());
        }else
        {
            throw new UserIdAlreadyExistException();
        }
    }

    // get user by userid rest api
    @GetMapping("/users/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable Long userid) {
        User user = userRepository.findById(userid);
        if(user==null)
        {
            throw new UserIdNotFoundException();
        }
        return ResponseEntity.ok(user);
    }

    // update user rest api
    @Async("asyncExecutor")
    @PutMapping("/sec/users/{userid}")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<User> updateUser(@PathVariable Long userid,
                @RequestBody User userDetails) {
    if(userRepository.update(new User(userid, userDetails.getUserName(), userDetails.getEmailId(), userDetails.getRole(), userDetails.getPassword()))==0)
                {
                    throw new UserIdNotFoundException();
                }

        return ResponseEntity.ok(userRepository.findById(userid));
    }

    // delete user rest api
    @Async("asyncExecutor")
    @DeleteMapping("/sec/users/{userid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Map<String, Boolean>> deleteUser
               (@PathVariable Long userid) {
        userRepository.deleteById(userid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
