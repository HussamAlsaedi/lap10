package com.example.jobs.Controller;

import com.example.jobs.ApiResponse.ApiResponse;
import com.example.jobs.Model.User;
import com.example.jobs.Repository.UserRepository;
import com.example.jobs.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

   @GetMapping("/get")
   public List<User> getAllUsers() {

       if (userService.findAllUsers().isEmpty()) {
           return null;
       }
      return userService.findAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@RequestBody @Valid User user, Errors errors) {

       if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
       }
       userService.addUser(user);
       return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer userId ,@RequestBody @Valid User user, Errors errors) {
       if (errors.hasErrors()) {
           String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
           return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
       }

       Boolean isUpdate = userService.updateUser(userId, user);

       if (isUpdate) {
           return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("User not updated"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {

       Boolean isDelete = userService.deleteUser(userId);
       if (isDelete) {
           return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("User not deleted"));
    }



}
