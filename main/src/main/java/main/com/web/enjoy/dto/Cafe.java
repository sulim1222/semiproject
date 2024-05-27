package main.com.web.enjoy.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cafe {
	private int cafeNo;
	private String cafeName;
	private String cafeAddress;
	private String cafePhone;
	private String cafeTime;
	private String cafeImg;
	private String location;
	private String category;
	private String cafeContent;
	//

}
