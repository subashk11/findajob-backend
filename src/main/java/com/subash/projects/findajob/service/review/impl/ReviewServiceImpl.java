package com.subash.projects.findajob.service.review.impl;

import com.mongodb.client.result.UpdateResult;
import com.subash.projects.findajob.entity.Review;
import com.subash.projects.findajob.repository.ReviewRepository;
import com.subash.projects.findajob.service.review.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Service
public class ReviewServiceImpl implements ReviewService {

    // Inject Repository to access mongo collections
    private ReviewRepository reviewRepository;
    private MongoTemplate mongoTemplate;

    // constructor based Injection
    public ReviewServiceImpl(ReviewRepository reviewRepository, MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review getReviewById(String companyId, String reviewId) {
        ObjectId objectId = new ObjectId(companyId);

        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(objectId).and("_id").is(reviewId));

        Review review = mongoTemplate.findOne(query, Review.class);
        return review;
    }

    @Override
    public List<Review> getAllReviewsOfCompany(String companyId) {

        ObjectId company_id = new ObjectId(companyId);

        Query query = new Query();
        query.addCriteria(Criteria.where("companyId").is(company_id));

        List<Review> reviewList = mongoTemplate.find(query, Review.class);
        return reviewList;
    }

    @Override
    public boolean deleteReview(String companyId, String reviewId) {

        ObjectId company_id = new ObjectId(companyId);

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(reviewId)).and("companyId").is(company_id));

        Review review = mongoTemplate.findOne(query, Review.class);

        if(review != null) {
            // delete the review
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(String companyId, String reviewId, Review updatedReview) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(reviewId).and("companyId").is(new ObjectId(companyId)));

        Update update = new Update().set("title", updatedReview.getTitle())
                .set("description", updatedReview.getDescription())
                .set("rating", updatedReview.getRating());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Review.class);

        if(result.wasAcknowledged()){
            return true;
        }
        return false;
    }

    @Override
    public boolean createReview(String companyId, Review review) {

        // convert string to objectId
        ObjectId company_id = new ObjectId(companyId);

        review.setCompanyId(company_id);
        Review cratedReview = reviewRepository.save(review);
        return true;
    }
}
