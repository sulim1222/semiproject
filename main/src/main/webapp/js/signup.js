/**
 * 
 */
let code ='';
for(let i =0; i<6; i++){
	code+=Math.floor(Math.random() * 10);
}
console.log(code);
let duplicateCh = false;
console.log("페이지 확인");
const $phone = document.getElementById("phone");
$phone.addEventListener("input",(e)=>{
    if(e.target.value.length>2){
        e.target.nextElementSibling.nextElementSibling.focus();
        console.log(e.target.value);  
    }
})
const $phone1 = document.getElementById("phone1");
$phone1.addEventListener("input",(e1)=>{
    if(e1.target.value.length>3){
        e1.target.nextElementSibling.nextElementSibling.focus();
    }
})
const $memberCheckNo = document.getElementById("memberCheckNo");
$memberCheckNo.addEventListener("input",(e)=>{
    if(e.target.value.length>5){
        e.target.nextElementSibling.nextElementSibling.focus();
    }
})
// ajax 내용
 // 이메일 중복체크 
const duplicate =()=> $.ajax({
	type : "post" , //ajax를  Post or Get 방식 선택 
	async : false, // 비동기 통신의 선택(true,false)
	url : 'http://localhost:9090/main/member/logincheck',
	dataType :'json',
	data : {
		userId : $('#userId').val(),
		emailDomain : $('#emailDomain').val()
	}, // 데이터 값 
	success : function(response){ // success : ajax통신 성공시 function : 함수 실행
		if(response.duplicate){
			alert("사용가능한 아이디입니다");
			console.log($("#btn1"));
			/*$("#btn1").attr("disabled","disabled");*/
			duplicateCh = true;
			console.log("체크방식안쪽"+duplicateCh);
		}else{
			alert("중복된 아이디입니다");
		}
	},
	error : function(xhr,status,error){
		console.log(error); // ajax 통신 실패시 
	}
})
const $allck = document.getElementById("allck");
$allck.addEventListener("click",(e)=>{
	if(duplicateCh){
		
	}else{
		console.log("체크안됨");
		 e.preventDefault(); // 전송을 막음
	}
})
// 이메일전송 부분
const sendMail =()=> $.ajax({
	type : "post",
	async : true,
	url : 'http://localhost:9090/main/member/sendEmail',
	dataType : 'json',
	data :{
		userId : $('#userId').val(),
		emailDomain : $('#emailDomain').val(),
		code :code
	},
	success : function(response){
		if(response.sendEmail){
			console(response)
			alert("이메일 전송했습니다");
			
		}
	}
	
})



