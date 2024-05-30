package main.com.web.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Rating {
	private int ratingNo;
	private int ratingScore;
	private int memberNo;
	private String category;
	private int entityId;
	

}
