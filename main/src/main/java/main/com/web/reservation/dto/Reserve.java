package main.com.web.reservation.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Reserve {
	private Integer reserveNo;
	private String location;
	private String memberId;
	private String memberName;
	private String roomType; //타입 지정
	private String bedType;
	private Date checkInDate; //check인 날짜
	private Date checkOutDate; // check아웃 날짜 지정
	private String memberPhone;
	private int payPrice;
	private int roomPeopleNo;
	private String memberAddress;
	private Date reserveDate;
		
}
