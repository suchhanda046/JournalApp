package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeatherTrackerService weatherTrackerService;

//    @GetMapping
//    public ResponseEntity<?> getUsers(){
//        List<User> all = userService.getAllUsers();
//        if(all!=null && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @GetMapping("id/{id}")
//    public ResponseEntity<?> getUserById(ObjectId id){
//        Optional<User> user = userService.getUserById(id);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User old = userService.findByUserName(userName);
        old.setUserName(user.getUserName());
        old.setPassword(user.getPassword());
        userService.createNewUser(old);
        return new ResponseEntity<>(old,HttpStatus.OK);

    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int temp = weatherTrackerService.getTemp("Mumbai").getCurrent().getFeelslike();
        return new ResponseEntity<>("Hi "+authentication.getName()+" WeatherTrackerService getFeelslike getFeelslike : "+temp,HttpStatus.OK);
    }
}
