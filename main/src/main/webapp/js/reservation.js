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
						
			}
			console.log(`${a[0]},${a[1]} 값전송성공`);
		}
	})
})
$("span").click(e=>{
	e.target
})

const ajaxScript = (num)=>{
	var pageSize = 10;
	var totalPage = 0;
	var curPage = num;
	$.ajax({
		url:"http://localhost:9090/main/reservation/date",
		type : "POST",
		data : {
			checkindate : a[0],
			checkoutdate : a[1],
			roomType : "Suite" //스위트로 설정 
		},
		dataType : "json",
		success:function(roomList){
			console.log(roomList);
			var totalCount = roomLis.length; // 받아온 객체의 전체값을 가져옴 
			if(totalCount !=0){
				totalPages =Math.ceil(totalCount/pageSize);
				var htmlStr = pageLink(curPage , totalPage ,"getAddr");
				
			}else{
				console.log("검색된 주소가 없음");
			}
		} 
	}) 
}



// ajax로 페이징처리
function pageLink(curPage, totalPages, funName){
	var pageUrl = "";
	var pageLimt = 5;
	var startPage = parseInt((curPage-1)/pageLimt) * pageLimt +1; //시작 페이지 
	// 1~ 5면 1~5page bar 형성 6이면 6~10까지 page 생성
	var endPage = startPage + pageLimt - 1;
	
	if(totalPages < endPage){
		endPage = totalPages;
	}
	var nextpage = endPage +1;
	
	if(curPage >1 && pageLimt <curPage){
		pageUrl += "<a href=javascript:"+funName + "(1)</a>";
		
	}
	if(curPage>pageLimt){
	
	}
	
}
