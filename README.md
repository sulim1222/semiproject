## 진행기간
2023.05.10 ~ 2023.05.29

## 개요
- 호텔 객실 예약관리

## 팀명 및 구성
- 팀명 : SENERGY
- 팀구성 : 팀장_강요한, 팀원_임성욱, 팀원_강동훈, 팀원_유선정, 팀원_김해진


## 사용된 기술
- FRONT_END :
- BACK_END :


## ⚙️ 기술 스택

|  카테고리  |                                                                                                                                                                                                 스택                                                                                                                                                                                                 |
| :--------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|통합개발환경|<img src="https://img.shields.io/badge/Eclipse IDE-181717?style=flat&logo=Eclipse IDE" />
|사용한 언어| <img src="https://img.shields.io/badge/Java-darkblue?style=flat&logo=Java" /> <img src="https://img.shields.io/badge/Javascript-4B4B77?style=flat&logo=Javascript" /> <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat&logo=jQuery" />
|프론트엔드|<img src="https://img.shields.io/badge/HTML 5-302683?style=flat&logo=HTML5" /> <img src="https://img.shields.io/badge/CSS 3-1572B6?style=flat&logo=CSS3" />| 
|형상관리도구|<img src="https://img.shields.io/badge/Github-181717?style=flat&logo=github&logoColor=white" />
|데이터베이스|<img src="https://img.shields.io/badge/OracleDB-F80000?style=flat&logo=Oracle&logoColor=white" />
|협업 도구|<img src="https://img.shields.io/badge/Discord-5865F2?style=flat&logo=discord&logoColor=white" /> <img src="https://img.shields.io/badge/KakaoOven-FFCD00?style=flat&logo=Kakao&logoColor=white" />|
|API|  <img src="https://img.shields.io/badge/Kakao-FFCD00?style=flat&logo=Kakao&logoColor=white" /> <img src="https://img.shields.io/badge/Portone-darkblue?style=flat" /> <img src="https://img.shields.io/badge/Navigator-302683?style=flat&logo=HTML5" /> <img src="https://img.shields.io/badge/Geolocation-302683?style=flat&logo=HTML5" />|
|SMTP|<img src="https://img.shields.io/badge/네이버SMTP-03C75A?style=flat&logo=naver&logoColor=white" />|
|WAS|<img src="https://img.shields.io/badge/Tomcat 9.0-F8DC75?style=flat&logo=Apache Tomcat&logoColor=black" />|



## 구현 기능과 역할
Header
1. 관리자 페이지에서 홈페이지 및 각 카테고리 페이지로 이동하는 링크 구현

관리자 페이지(매출현황)
1. 추가되는 예약들에 대해 지역별, 월별 매출 그래프 출력

관리자 페이지(예약리스트)
1. 예약 확정된 모든 예약들 확인 가능하도록 페이징 처리와 함께 기능 구현
2. 검색 타입에 따라 키워드를 통해 해당 단어가 포함된 예약들을 확인 가능
3. 지역별로 예약 확인 가능

관리자 페이지(신규 예약,수정 및 취소)
1. 홈페이지가 아닌 관리자를 통해서도 신규 예약이 가능
2. 입실 날짜는 오늘 날짜부터 선택 가능하며 퇴실 날짜는 입실 날짜부터 선택 가능
  

## 📷 담당한 서비스 스크린샷
![hompage_main](https://github.com/sulim1222/semiproject/assets/169763082/6afc196f-7100-4861-b665-44b5b1261089)

![main](https://github.com/sulim1222/semiproject/assets/169763082/cef980e0-9d02-40cb-ad51-183dce98f116)
![keyward_search](https://github.com/sulim1222/semiproject/assets/169763082/bafc7989-abc8-49ae-95b9-3a7d2aeb8404)
![new](https://github.com/sulim1222/semiproject/assets/169763082/f6cec911-d5d1-488c-87ac-88a6aad3582c)
![update](https://github.com/sulim1222/semiproject/assets/169763082/81c8aa9b-5aeb-4527-8c69-0045e95cd6e1)
![sales_by month](https://github.com/sulim1222/semiproject/assets/169763082/3e523ae9-bf7e-483e-a71b-4d116d9d1262)
![sales_by location](https://github.com/sulim1222/semiproject/assets/169763082/05da14ce-9410-4f89-8593-cec2751f9efe)


## 보완점 및 추가 기능 계획
보완점
1. 첫 프로젝트다 보니 코드의 복잡도와 자바스크립트를 이용한 기능들의 가독성이 떨어지는 부분에 대해서 보완이 필요
2. 디비 설계 미흡으로 인해 프로젝트 중간에 컬럼 및 테이블이 추가되는 부분이 있었는데 프로젝트 시작시 디비 설계와 UI의 중요성을 파악
3. 역할 분배가 제대로 이루어지지 않아 계획보다 달성률 미달이 심한 팀원도 있어서 적절한 분배의 필요성 파악

추가 기능 계획
1. 취소된 예약건들에 대해 리스트 생성하고 체크박스를 통해 다중 선택 취소 기능을 구현할 계획
2. 해당 일자의 객실 타입별 객실 재고 관리가 되는 기능을 구현할 계획

