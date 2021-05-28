package io.javabrains.moviecatalogservice;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service

public class MovieRating {

	@Autowired
	RestTemplate restTemplate ;
	

	@HystrixCommand(fallbackMethod = "getUserRatingFallback",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="6"),//last n requests
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="50"),//if fails 3 out of 6
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="5000")//sleep after pick up again
					
			})
	public UserRating getUserRating(String userId) {
		
		return restTemplate.getForObject("http://movie-rating-service/ratingdata/users/"+userId, UserRating.class);
	}
	public UserRating getUserRatingFallback(String userId) {
		return new UserRating(Arrays.asList(new Rating("Error Fall back", 0)));
	}

}
