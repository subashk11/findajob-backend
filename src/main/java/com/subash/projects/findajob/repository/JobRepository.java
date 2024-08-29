package com.subash.projects.findajob.repository;

import com.subash.projects.findajob.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JobRepository extends MongoRepository<Job, String> {
}
