package main.com.web.enjoy.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.com.web.review.dto.Review;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Food {
	private int foodNo;
	private String foodName;
	private String foodAddress;
	private String foodPhone;
	private String foodTime;
	private String foodImg;
	private String foodLatLong;
	private String location;
	private String category;
	private String foodContent;
	//
	private double averageRating; 
	 private List<Review> reviews;

}


