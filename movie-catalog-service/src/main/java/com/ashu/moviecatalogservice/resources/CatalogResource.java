package com.ashu.moviecatalogservice.resources;

import com.ashu.moviecatalogservice.model.CatalogItem;
import com.ashu.moviecatalogservice.model.Movie;
import com.ashu.moviecatalogservice.model.Rating;
import com.ashu.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream()
                .map(rating -> {
//                    For each movie Id, call MovieInfo service and get details
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
//                    Put all the data together
                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());

    }
}
                    /*
                    Movie movie = webClientBuilder.build()
                           .get()
                           .uri("http://localhost:8082/movies/" + rating.getMovieId())
                           .retrieve()
                           .bodyToMono(Movie.class)
                           .block();
                    */
