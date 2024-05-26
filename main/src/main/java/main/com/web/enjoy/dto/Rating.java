package main.com.web.enjoy.dto;

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
	private int MemberNo;
	private String category;
}
