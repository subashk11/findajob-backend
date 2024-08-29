package com.subash.projects.findajob.controller;

import com.subash.projects.findajob.entity.Review;
import com.subash.projects.findajob.service.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    // Inject service
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable("companyId") String companyId, @RequestBody Review review){
        boolean isCreated = reviewService.createReview(companyId, review);
        if(isCreated){
            return new ResponseEntity<>("Successfully added review to the company!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while adding review to the company!", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable("companyId") String companyId, @PathVariable("id") String reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);

        if(review.getId() != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }

        return new ResponseEntity<>("Review not Found!", HttpStatus.NOT_FOUND);

    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<String> updateReviewDetails(@PathVariable("companyId") String companyId,
                                                      @PathVariable("id") String reviewId, @RequestBody Review review){
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isUpdated){
            return new ResponseEntity<>("Review Updated Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while updating reviews for the company!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews(@PathVariable("companyId") String companyId){
        List<Review> reviewList = reviewService.getAllReviewsOfCompany(companyId);

        if(!reviewList.isEmpty()){
            return new ResponseEntity<>(reviewList, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Reviews found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("companyId") String companyId, @PathVariable("id") String reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while deleting review!", HttpStatus.NOT_FOUND);
    }

}
