/**
 * 
 */
    const date = new Date(); //현재 날짜
    console.log(date); // 
    date.toISOString().substring(0,10);
    console.log(date.toISOString().substring(0,10));
    console.log(typeof date.toISOString().substring(0,10));
   const a = new Array();
   const $showRoom = document.getElementById("showRoom");
  const checkindate1 = document.getElementById("ck1");
  const checkindate2 = document.getElementById("ck2");
  btn.addEventListener("click",e=>{
    a.pop();
    a.pop();
    checkindate1.innerText="체크인 날짜: ";
    checkindate2.innerText="체크아웃 날짜: ";
  })
  document.addEventListener('DOMContentLoaded', function() {
  
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    selectable: true,
    dateClick: function(info) { //클릭이벤트 
      // console.log(checkindate.innerHTML+=info.dateStr);
    if(info.dateStr<date.toISOString().substring(0,10)){
        alert(`${date.toISOString().substring(0,10)}이후 날짜를 선택해주세요`);}
    else{
      if(a.length<2){
      a.push(info.dateStr);
      console.log(typeof info.dateStr)
      a.sort();
      if(a[0]==undefined){
      }else{
        checkindate1.innerText="체크인 날짜: "+a[0];
      }
      if(a[1]==undefined){ 
      }else{
        checkindate2.innerText="체크아웃 날짜: "+a[1];
      }
    }else{
      alert("초기화 버튼을 눌러주세요");
    }
} 
    console.log(info.dateStr); // 시작날짜 
    },
      googleCalendarApiKey: 'AIzaSyCQuuUbjwcSDs4B2RUHNaSZF5zxqE8HucA',
        events: {
        googleCalendarId: 'b9ba28ab7d64f0f31ff2fb95137e51d6ef100eca797093fd5c5d6e36430f4c7e@group.calendar.google.com'
        }
    });
    calendar.render();
  });
btn1.addEventListener("click",(e)=>{
	$.ajax({
		type : "get",
		async : true,
		url : 'http://localhost:9090/main/reservation/date',
		dataType : "json",
		data : {
			checkindate : a[0],
			checkoutdate : a[1]
		},
			success :function(response){
			console.log(response);
			for(let i =0; i<response.length; i++){
			const $roomdiv = document.createElement('div');
			$roomdiv.className = "roomStyle";
			const $roomImg = document.createElement('img');
			$roomImg.src =`./imges/room/${i+1}-ST.png`;
			$roomdiv.appendChild($roomImg); //이미지 추가 
			showRoom.appendChild($roomdiv); //showRoom(div)추가 
				
			}
			console.log(`${a[0]},${a[1]} 값전송성공`);
		}
	})
})

StandardSelect.addEventListener("click",(e)=>{
	$.ajax({
		type : "get",
		async : true,
		url : 'http://localhost:9090/main/reservation/date',
		dataType : "json",
		data : {
			checkindate : a[0],
			checkoutdate : a[1],
			roomType : "Standard"
		},
			success :function(response){
			console.log(response);
			while($showRoom.firstChild){
				$showRoom.firstChild.remove();
			}
			for(let i =0; i<response.length; i++){
			const $roomdiv = document.createElement('div');
			$roomdiv.className = "roomStyle";
			const $roomImg = document.createElement('img');
			$roomImg.src =`./imges/room/${i+1}-ST.png`;
			$roomdiv.appendChild($roomImg); //이미지 추가 
			showRoom.appendChild($roomdiv); //showRoom(div)추가 
				
			}
			console.log(`${a[0]},${a[1]} 값전송성공`);
		}
	})
})

DeluxeSelect.addEventListener("click",(e)=>{
	$.ajax({
		type : "get",
		async : true,
		url : 'http://localhost:9090/main/reservation/date',
		dataType : "json",
		data : {
			checkindate : a[0],
			checkoutdate : a[1],
			roomType : "Deluxe"
		},
			success :function(response){
			console.log(response);
			while($showRoom.firstChild){
				$showRoom.firstChild.remove();
			}
			for(let i =0; i<response.length; i++){
			const $roomdiv = document.createElement('div');
			$roomdiv.className = "roomStyle";
			const $roomImg = document.createElement('img');
			$roomImg.src =`./imges/room/${i+1}-DL.png`;
			$roomdiv.appendChild($roomImg); //이미지 추가 
			showRoom.appendChild($roomdiv); //showRoom(div)추가 
				
			}
			console.log(`${a[0]},${a[1]} 값전송성공`);
		}
	})
})
SuiteSelect.addEventListener("click",(e)=>{
	$.ajax({
		type : "get",
		async : true,
		url : 'http://localhost:9090/main/reservation/date',
		dataType : "json",
		data : {
			checkindate : a[0],
			checkoutdate : a[1],
			roomType : "Suite"
		},
			success :function(response){
			console.log(response);
			while($showRoom.firstChild){
				$showRoom.firstChild.remove();
			}
			for(let i =0; i<response.length; i++){
				const $roomdiv = document.createElement('div'); //showroom에 들어갈 div
				$roomdiv.className = "roomStyle";  //shworoom에 들어갈
				const $roomImg = document.createElement('img');
				const $infoData = document.createElement('div'); //정보 및 데이터 
				const $reservationBtn = document.createElement('button'); //버튼생성				
				const $btnText = document.createTextNode("예약");
				const roomimgdiv =document.createElement('div');
				const reservationInfo = document.createElement('div');				
				$roomImg.src =`./imges/room/${i+1}-SW.png`;
				$($roomImg).css("width","230px");
				$($roomImg).css("height","150px");
				$($roomdiv).css("display","flex");
				
				$reservationBtn.appendChild($btnText);
				roomimgdiv.appendChild($roomImg); //이미지 추가
				reservationInfo.appendChild($reservationBtn);
				console.log($reservationBtn);
				$reservationBtn.className="btnType" //버튼 class 
				reservationInfo.className="reservationInfo"; // 버튼 및 정보 class 
				$roomdiv.appendChild(roomimgdiv); //이미지 추가 
				$roomdiv.appendChild(reservationInfo);
				showRoom.appendChild($roomdiv); //showRoom(div)추가 
			}
			console.log(`${a[0]},${a[1]} 값전송성공`);
		}
	})
})



