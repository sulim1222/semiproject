<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp" %>

<section id="new-container">

	<h2>예약 신규 등록</h2>
	<form action="<%=request.getContextPath() %>/reserve/insertnewendreserve.do" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="memberId" name="memberId" placeholder="아이디를 입력하세요">
				</td>
				<th>가격</th>
				<th>
					<input type="text" id="payPrice" name="payPrice" readonly>
				</th>
				<th>예약날짜</th>
				<th>
					<input type="date" id="reserveDate" name="reserveDate"  readonly>
				</th>
			</tr>
			<tr>
				<th>이름</th>
				<th>
					<input type="text" placeholder="이름을 입력하세요" name="memberName">
				</th>
				<th>연락처</th>
				<th>
					<input type="text" placeholder="(-)제외하고 입력해주세요"name="memberPhone">
				</th>
				<th>예약수정날짜</th>
				<th>
					<input type="date" id="updateReserveDate" name="updateReserveDate" readonly>
				</th>
			</tr>
			<tr>
				<th>인원수</th>
				<th>
					<select name="roomPeopleNo">
						<option value="1">1인</option>
						<option value="2">2인</option>
						<option value="3">3인</option>
						<option value="4">4인</option>
					</select>
				</th>
				<th>객실타입</th>
				<th>
					<select name="roomType">
							<option value="Standard">Standard</option>
							<option value="Deluxe">Deluxe</option>
							<option value="Suite">Suite</option>
					</select>
				</th>
				<th>베드타입</th>
				<th>
					<select name="bedType">
						<option value="twin">twin</option>
						<option value="double">double</option>
					</select>
				</th>	
			</tr>
			<tr>
				<th>지역</th>
				<th>
					<select name="location">
						<option value="서울">서울</option>
						<option value="부산">부산</option>
						<option value="제주">제주</option>
					</select>
				</th>
				<th>입실날짜</th>
				<th>
					<input type="date" name="checkInDate">
				</th>
				<th>퇴실날짜</th>
				<th>
					<input type="date" name="checkOutDate">
				</th>
			</tr>
			<tr>
				<th >주소</th>	
				<td colspan="5">
					<input type="text" placeholder="주소를 입력하세요" name="memberAddress" style="width: 95%;">	
				</td>
			</tr>
			<tr>
				<td>메모사항</td>
				<td colspan="5"><textarea name="requstMemo" cols="128" rows="10" placeholder="고객요청사항을 적으세요" style="resize:none;"></textarea></td>
			</tr>
		</table>
			
			<input type="submit" value="등록">
			
	</form>
</section>	

<script>
   //입실날짜부터 선택해서 퇴실날짜 선택가능하게
    const checkInDateInput = document.querySelector('input[name="checkInDate"]');
	const checkOutDateInput = document.querySelector('input[name="checkOutDate"]');
	
	checkInDateInput.min = new Date().toISOString().split('T')[0];
	
	checkInDateInput.addEventListener('change', function() {
	    checkOutDateInput.min = checkInDateInput.value;
	});
	    
	    //객실타입에 따라 객실료 자동으로 설정 readonly
    const roomTypeSelect = document.querySelector('select[name="roomType"]');
	const payPriceInput = document.querySelector('input[name="payPrice"]');
	
	window.addEventListener('load', function() {
	    const firstRoomType = 'Standard';
	    const firstPrice = getPayPrice(firstRoomType);
	    roomTypeSelect.value = firstRoomType;
	    payPriceInput.value = firstPrice; 
	});
	
	
	roomTypeSelect.addEventListener('change', function() {
	    const roomType = roomTypeSelect.value;
	    const price = getPayPrice(roomType);
	    payPriceInput.value = price;
	});
	
	function getPayPrice(roomType){
	    if (roomType === 'Standard') {
	        return '100000';
	    } else if (roomType === 'Deluxe') {
	        return '200000';
	    } else if (roomType === 'Suite') {
	        return '300000';
	    }
	}
	
	document.getElementById('reserveDate').valueAsDate = new Date();
	document.getElementById('updateReserveDate').valueAsDate = new Date();
</script>


<style>
     body {
         background-color: lightgray;
         margin: 0;
         padding: 0;
     }
     #new-container {
         width: 60%;
         margin: 2rem auto;
         background-color: white;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
         border-radius: 8px;
         overflow: hidden;
     }
     h2 {
         background-color: lightgray;
         margin: 10px;
         padding: 1rem;
         text-align: center;
     }
     form {
         padding: 1rem;
     }
     table {
         width: 100%;
         border-collapse: collapse;
         margin-bottom: 30px;
         
     }
     th, td {
         padding: 0.75rem;
         text-align: left;
         background-color: lightgray;
         border: 1px solid black;
         text-align: center;
     }
     
     td input[type="text"],
     th input[type="text"], 
     td input[type="date"],
     th input[type="date"],
     td select, 
     th select{
     	 width: 180px;
         border: 1px solid lightgray;
         border-radius: 10px;
         height: 30px;
         text-align: center;
     }
     
     
     textarea {
         resize: none;
         border-radius: 10px;
         text-align: center;
     }
     input[type="submit"] {
         display: block;
         width: 100%;
         padding: 1rem;
         background-color: lightgray;
         border: none;
         color: black;
         border-radius: 4px;
         font-size: 1rem;
         cursor: pointer;
         
     }
     input[type="submit"]:hover {
         background-color: lightblue;
     }
</style>
<%@ include file="/WEB-INF/views/common/adminfooter.jsp"%>