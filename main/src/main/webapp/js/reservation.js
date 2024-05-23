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
btn1.addEventListener("click", e => {
    fetchRooms("Standard", 1);
});

StandardSelect.addEventListener("click", e => {
    fetchRooms("Standard", 1);
});

DeluxeSelect.addEventListener("click", e => {
    fetchRooms("Deluxe", 1);
});

SuiteSelect.addEventListener("click", e => {
    fetchRooms("Suite", 1);
});

function fetchRooms(roomType, page) {
    $.ajax({
        type: "get",
        async: true,
        url: 'http://localhost:9090/main/reservation/date',
        dataType: "json",
        data: {
            checkindate: a[0],
            checkoutdate: a[1],
            roomType: roomType,
            page: page,
            itemsPerPage: 3 // 페이지 당 아이템 수 설정
        },
        success: function (response) {
            const roomList = response.roomList;
            const totalData = response.totalData;
            while ($showRoom.firstChild) {
                $showRoom.firstChild.remove();
            }
            roomList.forEach((room, i) => {
                const $roomdiv = document.createElement('div');
                $roomdiv.className = "roomStyle";
                const $roomImg = document.createElement('img');
                $roomImg.src = `./imges/room/${room.roomUrl}`;
                const $roomInfo = document.createElement('p');
                $roomInfo.textContent = `Room No: ${room.roomNo}, Price: ${room.roomPrice}, Amenity: ${room.roomAmenity}, Area: ${room.roomArea}, Info: ${room.roomInfo}, Location: ${room.location}, Category: ${room.category}, Service: ${room.hotelService}`;
                $roomdiv.appendChild($roomImg);
                $roomdiv.appendChild($roomInfo);
                $showRoom.appendChild($roomdiv);
            });
            updatePageBar(page, totalData, roomType);
            console.log(`${a[0]}, ${a[1]} 값 전송 성공`);
        }
    });
}

function updatePageBar(curPage, totalData, roomType) {
    const itemsPerPage = 3;
    const totalPages = Math.ceil(totalData / itemsPerPage);
    const pageLimit = 5;
    let startPage = parseInt((curPage - 1) / pageLimit) * pageLimit + 1;
    let endPage = startPage + pageLimit - 1;
    endPage = totalPages < endPage ? totalPages : endPage;

    let pageUrl = "";

    if (curPage > 1) {
        pageUrl += `<a href='javascript:fetchRooms("${roomType}", ${curPage - 1})'>이전</a>&nbsp;&nbsp;`;
    }

    for (let i = startPage; i <= endPage; i++) {
        if (i === curPage) {
            pageUrl += `<strong>${i}</strong>&nbsp;&nbsp;`;
        } else {
            pageUrl += `<a href='javascript:fetchRooms("${roomType}", ${i})'>${i}</a>&nbsp;&nbsp;`;
        }
    }

    if (endPage < totalPages) {
        pageUrl += `<a href='javascript:fetchRooms("${roomType}", ${endPage + 1})'>다음</a>&nbsp;&nbsp;`;
    }

    document.getElementById("pageBar").innerHTML = pageUrl;
}
