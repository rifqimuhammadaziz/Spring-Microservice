package com.rifqimuhammadaziz.moviecatalogservice.resources;

import com.rifqimuhammadaziz.moviecatalogservice.models.CatalogItem;
import com.rifqimuhammadaziz.moviecatalogservice.models.Movie;
import com.rifqimuhammadaziz.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        
        List<Rating> ratings = Arrays.asList(
                new Rating("Movie001", 5),
                new Rating("Movie002", 3),
                new Rating("Movie003", 2)
        );

        return ratings.stream().map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getMovieName(), "Test Desc", rating.getRating());
                })
                .collect(Collectors.toList());

        // get all rated movie IDs

        // for each movie ID, call movie info service and get details

        // put them all

    }
}
