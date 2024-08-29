package com.subash.projects.findajob.service.job.Impl;

import com.mongodb.client.result.UpdateResult;
import com.subash.projects.findajob.entity.Job;
import com.subash.projects.findajob.repository.JobRepository;
import com.subash.projects.findajob.service.job.JobService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class JobServiceImpl implements JobService {

//    private static List<Job> jobList = new ArrayList<Job>();
//
//    // sample list of jobs
//    static {
//        jobList.add(new Job(1l, "Software Engineer",  "Java, Python, JavaScript", "150000", "100000", "chennai"));
//        jobList.add(new Job(2l, "Data Scientist",   "Python, R, SQL", "180000","1400000", "pune"));
//        jobList.add(new Job(3l, "Product Manager",  "Agile, Scrum", "160000", "100000", "hyderabad"));
//    }


    private JobRepository jobRepository;
    private MongoTemplate mongoTemplate;

    public JobServiceImpl(JobRepository jobRepository, MongoTemplate mongoTemplate){
        this.jobRepository = jobRepository;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(String jobId) {
        jobRepository.deleteById(jobId);
        return false;
    }

    @Override
    public Optional<Job> getJob(String jobId) {
        Optional<Job> job = jobRepository.findById(jobId);
        return job;
    }

    @Override
    public boolean updateJobDetails(String jobId, Job jobInfo) {
        // Query to get job by id
        Query query = new Query(where("_id").is(jobId));
        Update update = new Update().set("title", jobInfo.getTitle())
                .set("description", jobInfo.getDescription())
                .set("jobLocation", jobInfo.getJobLocation())
                .set("minSalary", jobInfo.getMinSalary())
                .set("maxSalary", jobInfo.getMaxSalary())
                .set("companyId", jobInfo.getCompanyId());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Job.class);

        return true;
    }
}
