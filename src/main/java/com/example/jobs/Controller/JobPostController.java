package com.example.jobs.Controller;

import com.example.jobs.ApiResponse.ApiResponse;
import com.example.jobs.Model.JobPost;
import com.example.jobs.Model.User;
import com.example.jobs.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobPost")
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public List<JobPost> getAllJobPost() {

        return jobPostService.getAllJobPosts();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {

        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(201).body(new ApiResponse("JobPost added successfully"));
    }
    @PutMapping("/update/{jobPostId}")
    public ResponseEntity<ApiResponse> updateJobPost(@PathVariable Integer jobPostId ,@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }

        Boolean isUpdate = jobPostService.updateJobPost(jobPostId,jobPost);

        if (isUpdate) {
            return ResponseEntity.status(201).body(new ApiResponse("JobPost updated successfully"));
        }

        return ResponseEntity.status(404).body(new ApiResponse("JobPost not found"));
    }

    @DeleteMapping("/delete/{jobPostId}")
    public ResponseEntity<ApiResponse> deleteJobPostById(@PathVariable Integer jobPostId) {

        Boolean isDelete = jobPostService.deleteJobPostById(jobPostId);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("jobPostId deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("jobPostId not deleted"));
    }



}
