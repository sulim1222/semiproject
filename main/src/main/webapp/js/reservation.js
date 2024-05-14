/**
 * 
 */
    const date = new Date(); //현재 날짜
    console.log(date); // 
    date.toISOString().substring(0,10);
    console.log(date.toISOString().substring(0,10));
    console.log(typeof date.toISOString().substring(0,10));
   const a = new Array();
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
			console.log(`${a[0]},${a[1]} 값전송성공`);
		}
	})
})
