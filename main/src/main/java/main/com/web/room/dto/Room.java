package main.com.web.room.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

	//DB ROOM테이블(+ROOM ATTACH)을 자바 Room객체로 저장하기 위해 만든 틀?
	
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
	private List<RoomImages> roomImages; // 이미지 목록을 저장할 필드 추가?
}
