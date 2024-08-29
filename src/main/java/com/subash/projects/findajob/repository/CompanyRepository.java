package com.subash.projects.findajob.repository;

import com.subash.projects.findajob.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
