package main.com.web.pay.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payment {
	private int payNo;
	private int reserveNo;
	private int payCoupon;
	private String payMethod;
	private int payPrice;
	private int payDate;
	private int payBank;
	private String hotelNo;
	
	
}
