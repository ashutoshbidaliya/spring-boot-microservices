package com.ashu.ratingdataservice.resources;

import com.ashu.ratingdataservice.models.Rating;
import com.ashu.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
    @RequestMapping("/ratingsdata")
public class RatingsResources {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userID){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;

    }
}
