package com.subash.projects.findajob.service.job;

import com.subash.projects.findajob.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface JobService {

    List<Job> getAllJobs();
    void createJob(Job job);
    boolean deleteJob(String id);
    Optional<Job> getJob(String id);
    boolean updateJobDetails(String id, Job job);

}
