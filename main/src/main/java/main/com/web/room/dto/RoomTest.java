package main.com.web.room.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RoomTest {
	@SerializedName("roomNo")
	private Integer roomNo;
	
	@SerializedName("roomPrice")
	private Integer roomPrice;
	
	@SerializedName("location")
	private String location;
	
	@SerializedName("roomAmenity")
	private String roomAmenity;
	
	@SerializedName("roomCount")
	private Integer roomCount;
	
	@SerializedName("rooArea")
	private Integer rooArea;
	
	@SerializedName("roomPeoleNo")
	private Integer roomPeoleNo;
	
	@SerializedName("roomType")
	private String roomType;
	
	@SerializedName("roomInform")
	private String roomInform;
	
	@SerializedName("bedType")
	private  String bedType;
}
