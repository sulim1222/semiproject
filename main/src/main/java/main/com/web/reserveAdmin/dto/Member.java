package main.com.web.reserveAdmin.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

	private String memberId;
	private int reserveNo;
	private String location;
	private String memberName;
	private String roomType;
	private String bedType;
	private Date checkInDate;
	private Date checkOutDate;
	private String memberPhone;
	private int payPrice;
	private int roomPeopleNo;
	private String memberAddress;
	private Date reserveDate;
}
