package com.example.jobs.Controller;

import com.example.jobs.ApiResponse.ApiResponse;
import com.example.jobs.Model.JobApplication;
import com.example.jobs.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobApplication")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public List<JobApplication> getJobApplications() {
        return jobApplicationService.findAll();
    }

@PostMapping("/add/{jobApplicationId}")
    public ResponseEntity<ApiResponse> addJobApplication(@PathVariable Integer jobApplicationId, @RequestBody @Valid JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().get(0).getDefaultMessage();
            return  ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }

        Integer status = jobApplicationService.addJobApplication(jobApplicationId, jobApplication);

        return switch (status) {
            case 1 -> ResponseEntity.status(404).body(new ApiResponse("User not found"));
            case 2 -> ResponseEntity.status(409).body(new ApiResponse("Job not found"));
            default -> ResponseEntity.status(201).body(new ApiResponse("JobApplication added successfully"));
        };
    }

    @DeleteMapping("delete/{jobApplicationId}")
    public ResponseEntity<ApiResponse> deleteJobApplication(@PathVariable Integer jobApplicationId) {

        boolean isDeleted = jobApplicationService.deleteJobApplicationById(jobApplicationId);

        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("JobApplication deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("JobApplication not found"));
    }
}
