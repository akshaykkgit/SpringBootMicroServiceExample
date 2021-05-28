package com.example.ratingdataservice.resorse;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingdataservice.model.Rating;
import com.example.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingrRsource {
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating("1023", 1);
	}
	
	@RequestMapping("users/{userId}")
	
	public UserRating getUserRating(@PathVariable String userId) {
		List<Rating> ratings= Arrays.asList(
new Rating("123",10),
new Rating("124",8)
				);
		
		return new UserRating(ratings);
	}

}
