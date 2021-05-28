package io.javabrains.moviecatalogservice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	RestTemplate restTemplate ;
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	
	MovieRating movieRating;
	
	@RequestMapping("/{userId}")
	
	public List<CatalogItem> getCatalog(@PathVariable("userId") String  userId){
		
		//WebClient.Builder builder =WebClient.builder();
		
		UserRating ratings =movieRating.getUserRating(userId);

		return ratings.getUserRating().stream().map(rating->movieInfo.getMovieInfo(rating)).collect(Collectors.toList());
				
		
		
	}
	

	

}
