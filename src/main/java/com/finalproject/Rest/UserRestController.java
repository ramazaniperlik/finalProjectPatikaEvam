package com.finalproject.Rest;

import com.finalproject.Entity.User;
import com.finalproject.Service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserRestController {

    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            return ResponseEntity.ok().body(userService.addUser(user));
        }catch(Exception e){
            return new ResponseEntity<>("User is not found!!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok().body(id+". user is deleted");
        }catch (Exception e){
            return new ResponseEntity<>("Fail Request!",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id){
        try{
            return ResponseEntity.ok().body(userService.updateUser(user,id));
        }catch(Exception e){
            return new ResponseEntity<>("User is not found!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUsers")
    ResponseEntity<?> getUsers(){
        if(userService.getUsers().size()==0)
             return new ResponseEntity<>("User is not found!", HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUser(@PathVariable int userId){
        try{
            return ResponseEntity.ok().body(userService.getUserByUserId(userId));
        }catch(Exception e){
            return new ResponseEntity<>("User is not found!",HttpStatus.NOT_FOUND);
        }
    }
}
