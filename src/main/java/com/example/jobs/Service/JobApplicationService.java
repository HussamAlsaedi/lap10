package com.example.jobs.Service;

import com.example.jobs.Model.JobApplication;
import com.example.jobs.Model.JobPost;
import com.example.jobs.Model.User;
import com.example.jobs.Repository.JobApplicationRepository;
import com.example.jobs.Repository.JobPostRepository;
import com.example.jobs.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> findAll() {
        return jobApplicationRepository.findAll();
    }

public int addJobApplication(Integer jobPostId, JobApplication jobApplication) {

        List<User> users = userRepository.findAll();
        List<JobPost> jobPosts = jobPostRepository.findAll();

        Integer u1 = null;
        Integer j1 = null;

        for (User user : users) {
             if (jobApplication.getUserId() == (user.getId())) {
                u1 = user.getId();

             }
        }

        if (u1 == null) {
            return 1;
        }

        for (JobPost jobPost : jobPosts) {
            if (jobPost.getId() == jobPostId) {
                j1 = jobPost.getId();
            }
        }

    if (j1 == null) {
        return 2;
    }

    jobApplicationRepository.save(jobApplication);
        return 0;
    }

    public boolean deleteJobApplicationById(Integer jobApplicationId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();

        for (JobApplication jobApplication : jobApplications) {
            if (jobApplication.getId() == jobApplicationId) {
                jobApplicationRepository.delete(jobApplication);
                return true;
            }
        }
        return false;
    }
}


