package main.com.web.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RoomImages {
	
	private int roomAttachNo;
	private String roomAttachName;
	private int roomRef;
	
}
