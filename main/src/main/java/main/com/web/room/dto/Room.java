package main.com.web.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

	private int roomNo;
	private int roomPrice;
	private String roomAmenity;
	private float roomArea;
	private String roomType;
	private String roomInfo;
	private String location;
	private String category;
	private String hotelService;
	private String roomUrl;

}
