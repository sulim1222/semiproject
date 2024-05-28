/**
 * 
 */
// 중복을 먼저 체크하고 , 이메일 전송이 되는것을 구현 못함

//$("#checkMail").css("borderColor","red");//배경 테두리 색 변경
let $duplicate = false; //중복 check 최종 가입버튼 체크를 하기위함
let $emilCheck = false;
let $pwdCheck = false;
let $pwdCheckConf = false;
let $phoneck =false;
let $phone1ck =false;
let $phone2ck =false;
let $memberCheckNoEndck =false;
let $memberCheckNock =false;

const pwdRex = /^(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,16}$/;
console.dir($("#checkMail"));
var code = "";
console.log("페이지 확인");
const $phone = document.getElementById("phone");
$phone.addEventListener("keyup",(e)=>{
    if(e.target.value.length>2){
		$phoneck =true;
		$("#phone").css("borderColor","green");
        e.target.nextElementSibling.nextElementSibling.focus();
        console.log(e.target.value);  
    }else{
		$phoneck =false;
	}
})
const $phone1 = document.getElementById("phone1");
$phone1.addEventListener("keyup",(e1)=>{
    if(e1.target.value.length>3){
		$phone1ck =true;
		$("#phone1").css("borderColor","green");
        e1.target.nextElementSibling.nextElementSibling.focus();
    }else{
		$phone1ck =false;
	}
})
phone2.addEventListener("keyup",(e1)=>{
    if(e1.target.value.length>3){
		$phone2ck =true;
		$("#phone2").css("borderColor","green");
    }else{
		$phone2ck =false;
	}
})
const $memberCheckNo = document.getElementById("memberCheckNo");
$memberCheckNo.addEventListener("input",(e)=>{
    if(e.target.value.length==6){
		$("#memberCheckNo").css("borderColor","green");
		console.log("dkdkkd");
		$memberCheckNock =true;
        e.target.nextElementSibling.nextElementSibling.focus();
    }else{
		$memberCheckNock =false;
	}
})
// ajax 내용
 // 이메일 중복체크 
const duplicate =()=> $.ajax({
	type : "post" , //ajax를  Post or Get 방식 선택 
	async : false, // 비동기 통신의 선택(true,false)
	url : 'http://14.36.141.71:9994:10079/GDJ79_main_semi/member/logincheck',
	dataType :'json',
	data : {
		userId : $('#userId').val(),
		emailDomain : $('#emailDomain').val()
	}, // 데이터 값 
	success : function(response){ // success : ajax통신 성공시 function : 함수 실행
		if(response.duplicate){
			alert("사용가능한 아이디입니다");
			console.log($("#btn2"));
			//show할수있게끔 중복확인되면  이메일 전송칸 보이게끔 로직구현
			/*$("#btn1").attr("disabled","disabled");*/
			$("#btn2").css("visibility","visible");
			console.log("dd");
			$duplicate = true;
		}else{
			alert("중복된 아이디입니다");
		}
	},
	error : function(xhr,status,error){
		console.log(error); // ajax 통신 실패시 
	}
})
// 이메일전송 부분
const sendMail =()=> $.ajax({
	type : "post",
	async : true,
	url : 'http://14.36.141.71:9994:10079/GDJ79_main_semi/member/sendEmail',
	dataType : 'json',
	data :{
		userId : $('#userId').val(),
		emailDomain : $('#emailDomain').val(),
	},
	success : function(response){
		if(response.sendEmail){
			sessionStorage.setItem("mailcode",response.code); //key value 방식으로  세션값을 저장
			document.getElementById("btn2").innerText="재전송";
			code = sessionStorage.getItem("mailcode");
			$("#sibal").css("visibility","visible");
			setTimeout((e)=>{
				sessionStorage.clear();
			},1800)
			console.log(sessionStorage.getItem("mailcode"));
			alert(response.code);
		}
	}
});
//이메일 전송 코드 확인 하는 로직 5/15 값 같을시 true false반환해서 추가적인 로직구현
const checkMailbtn =()=> $.ajax({
		type : "post",
		async : true,
		url : "http://14.36.141.71:9994:10079/GDJ79_main_semi/member/mailck",
		dataType : 	"json",
		data : {
			checkMail : $("#checkMail").val(),
			mailcode : code
		},
		success : function(response){
			if(response.emailOk){  //true시 
			$("#btn2").attr("disabled",true);
			$("#checkMail").css("borderColor","green");
			$("#userId").attr("readonly",true);	 // 변경 안되겠끔 
			$("#checkMail").attr("readonly",true); // 이메일 칸 전송에서 변경을 막음
			$emilCheck = true;
			}else{
				alert("다시한번 확인해주세요");
			}	
		}
})
memberPwd.addEventListener("keyup",e=>{
	if(pwdRex.test(e.target.value)){
		$pwdCheck = true;
		$("#memberPwd").css("borderColor","green");
	}else{
		$pwdCheck = false;
	}
})
memberPwdConfirm.addEventListener("keyup",e=>{
	if(e.target.value==memberPwd.value){
		$pwdCheckConf =true;
		 $("#memberPwdConfirm").css("borderColor","green");
		console.log(e.target.value);
	}else{
		$pwdCheckConf =false;
	}
})
memberPwdConfirm.addEventListener("keyup",e=>{
	if(e.target.value==memberPwd.value){
		$pwdCheckConf =true;
		 $("#memberPwdConfirm").css("borderColor","green");
		console.log(e.target.value);
	}else{
		$pwdCheckConf =false;
	}
})
memberPwdConfirm.addEventListener("keyup",e=>{
	if(e.target.value==memberPwd.value){
		$pwdCheckConf =true;
		 $("#memberPwdConfirm").css("borderColor","green");
		console.log(e.target.value);
	}else{
		$pwdCheckConf =false;
	}
})
memberPwdConfirm.addEventListener("keyup",e=>{
	if(e.target.value==memberPwd.value){
		$pwdCheckConf =true;
		 $("#memberPwdConfirm").css("borderColor","green");
		console.log(e.target.value);
	}else{
		$pwdCheckConf =false;
	}
})
allck.addEventListener("click",e=>{
 if(!($pwdCheckConf && $duplicate && $emilCheck && $pwdCheck && $memberCheckNoEndck && 
      $memberCheckNock && $phoneck && $phone1ck && $phone2ck)){
	e.preventDefault();
	if(!$pwdCheck){
		$("#memberPwd").css("borderColor","red");
	}else if(!$pwdCheckConf){
	 $("#memberPwdConfirm").css("borderColor","red");
			/*$("#checkMail").css("borderColor","red");*/
	}
	else if(!$duplicate){
		alert("이메일전송 버튼을 눌러주세요");
	}else if(!$emilCheck){
		alert("이메일전송코를 작성후 확인버튼을 눌러주세요");
	}else if($("#memberName").val().length==0){
		$("#memberName").css("borderColor","red");
	}else if(!$memberCheckNoEndck){
		$("#memberCheckNoEnd").css("borderColor","red");
	}else if(!$memberCheckNock){
		$("#memberCheckNo").css("borderColor","red");
	}else if(!$phoneck){
		$("#phone").css("borderColor","red");
	}else if(!$phone1ck){
		$("#phone1").css("borderColor","red");
	}else if(!$phone2ck){
		$("#phone2").css("borderColor","red");
	}
 }
})
$("#memberName").keyup(e=>{
	if($("#memberName").val().length==0){
		
	}else{
		$("#memberName").css("borderColor","green");
	}
})
//주민번호 뒷자리 
$("#memberCheckNoEnd").keyup(e=>{
	if($("#memberCheckNoEnd").val().length==0){
		$memberCheckNoEndck =false;
	}else{
		$("#memberCheckNoEnd").css("borderColor","green");
		$memberCheckNoEndck =true;
	}
})
/*let $phoneck =false;
let $phone1ck =false;
let $phone2ck =false;
let $memberCheckNoEndck =false;
let $memberCheckNock =false*/

