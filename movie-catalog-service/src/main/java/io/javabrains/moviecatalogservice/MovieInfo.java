package io.javabrains.moviecatalogservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
public class MovieInfo {
	
	@Autowired
	RestTemplate restTemplate ;
	
	@HystrixCommand(fallbackMethod = "getMovieInfoFallback")
	public CatalogItem getMovieInfo(Rating rating) {
		Movie movie =restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getMovieId(), movie.getName(), rating.getRating());
		
		/*Movie movie =webClientBuilder.build()
		.get()
		.uri("http://localhost:8081/movies/"+rating.getMovieId())
		.retrieve()
		.bodyToMono(Movie.class)
		.block();
		return new CatalogItem(movie.getName(), "Desc", rating.getRating());*/
		
	}
	public CatalogItem getMovieInfoFallback(Rating rating) {
		return new CatalogItem("No Info", "Fallback Error", 0);
	}

}
