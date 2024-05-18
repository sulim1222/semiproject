package main.com.web.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTest {
	private Integer roomNo;
	private Integer roomPrice;
	private String location;
	private String roomAmenity;
	private Integer roomCount;
	private Integer rooArea;
	private Integer roomPeoleNo;
	private String roomType;
	private String roomInform;
	private  String bedType;
}
