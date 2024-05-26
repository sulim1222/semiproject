package main.com.web.admin.reserve.dto;

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
	private String reserveNo;
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
	private Date updateReserveDate;
	private String requestMemo;
	private String memberNo;
}
