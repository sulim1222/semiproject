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


public class Tour {
	private int tourNo;
	private String tourName;
	private String tourAddress;
	private String tourPhone;
	private String tourTime;
	private String tourImg;
	private String tourLatLong;
	private String location;
	private String category;
	private String tourContent;
	//
	private double averageRating; 
	 private List<Review> reviews;
}
