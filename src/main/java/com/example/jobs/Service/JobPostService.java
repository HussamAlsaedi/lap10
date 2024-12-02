package com.example.jobs.Service;

import com.example.jobs.Model.JobPost;
import com.example.jobs.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }
    public void addJobPost(JobPost jobPost) {
         jobPostRepository.save(jobPost);
    }

    public boolean updateJobPost(Integer jobPostID, JobPost jobPost) {
            JobPost oldJobPost = jobPostRepository.getById(jobPostID);

            if (oldJobPost != null) {
                oldJobPost.setTitle(jobPost.getTitle());
                oldJobPost.setDescription(jobPost.getDescription());
                oldJobPost.setPostingDate(jobPost.getPostingDate());
                jobPostRepository.save(oldJobPost);
                return true;
            }
            return false;
    }

    public boolean deleteJobPostById(Integer jobPostID) {
        List<JobPost> jobPosts = jobPostRepository.findAll();

        for (JobPost jobPost : jobPosts) {
            if (jobPostID.equals(jobPost.getId())) {
                jobPostRepository.delete(jobPost);
            }
        }
        return false;
    }
}
