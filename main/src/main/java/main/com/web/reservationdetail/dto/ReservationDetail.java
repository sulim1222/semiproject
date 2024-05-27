package main.com.web.reservationdetail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDetail {
	
	private String reserveNo;
	private int roomNo;
	private String request;
	private String car;
	private String bedType;
	private int roomPeopleNo;

}
