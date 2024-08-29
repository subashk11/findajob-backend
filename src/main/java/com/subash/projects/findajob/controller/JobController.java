package com.subash.projects.findajob.controller;

import com.subash.projects.findajob.entity.Job;
import com.subash.projects.findajob.service.job.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    // Service
    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    // API
    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJobs(){
        List<Job> jobList = jobService.getAllJobs();
        if(jobList.size() > 0){
            return new ResponseEntity<>(jobList, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Jobs Currently!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJobById(@PathVariable("id") String jobId){
        Optional<Job> resultJob = jobService.getJob(jobId);
        if(resultJob.isPresent())
            return new ResponseEntity<>(resultJob, HttpStatus.OK);
        return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
       jobService.createJob(job);
       return ResponseEntity.ok("Job created successfully");
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") String jobId){
        boolean isDeleted = jobService.deleteJob(jobId);
        if(isDeleted){
            return ResponseEntity.ok("Deleted Successfully");
        }
        return new ResponseEntity<>("Job not found!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<?> updateJobDetails(@PathVariable("id") String jobId, @RequestBody Job job){
        boolean isJobUpdated = jobService.updateJobDetails(jobId, job);
        if(isJobUpdated){
            return new ResponseEntity<>("Successfully updated details!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while updating job details!", HttpStatus.NOT_FOUND);
    }
}
