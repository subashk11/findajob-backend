package com.subash.projects.findajob.repository;

import com.subash.projects.findajob.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

}
