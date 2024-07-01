## 📆 진행기간
2023.05.10 ~ 2023.05.29



## ✒️ 개요
- 호텔 객실 예약관리



## 🚶 팀명 및 구성
- 팀명 : SENERGY
- <table>
  <tbody>
    <tr>
      <td align="center"><sub><b>팀장 : 강요한</b></sub></a><br /></td>
      <td align="center"><sub><b>팀원 : 임성욱</b></sub></a><br /></td>
      <td align="center"><sub><b>팀원 : 강동훈</b></sub></a><br /></td>
      <td align="center"><sub><b>팀원 : 유선정</b></sub></a><br /></td>
      <td align="center"><sub><b>팀원 : 김해진</b></sub></a><br /></td>
    </tr>
  </tbody>
</table>



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




## 📜 구현 기능과 역할
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

1. ERD cloud
![erd](https://github.com/sulim1222/semiproject/assets/169763082/77cbc2ac-9313-4eeb-9940-95a174f7a0ba)

- 프로젝트 시작 전 철저한 ERD 설계가 전체 개발 과정의 효율성을 크게 높일 수 있다는 것을 실감 했습니다.
- 프로젝트의 목적과 요구 사항을 깊이 이해하는 것이 좋은 ERD 설계의 기초가 됨을 알게 되었습니다.
<br>
2. 사용자 페이지 메인
![hompage_main](https://github.com/sulim1222/semiproject/assets/169763082/6afc196f-7100-4861-b665-44b5b1261089)

- UI 설계 계획: 각 지역별로 특색 있는 디자인을 적용하여 사용자들에게 시각적으로 차별화된 경험을 제공하고자 했습니다.
서울은 현대적이고 세련된 디자인, 제주는 자연친화적이고 휴양지 느낌의 디자인, 부산은 해변과 도시가 공존하는 분위기의 디자인을 구상했습니다.
- 백엔드 구현 계획:
각 지역별로 독립적인 데이터베이스를 구축하여 지역 특성에 맞는 데이터 관리를 하고자 했습니다.

초기 계획은 프로젝트의 규모와 복잡성을 크게 증가시킬 수 있다는 점을 고려하여, 최종적으로는 더욱 집중적이고 관리 가능한 범위로 프로젝트를 조정하게 되었습니다.
<br>
3. 관리자 페이지 메인
![main](https://github.com/sulim1222/semiproject/assets/169763082/cef980e0-9d02-40cb-ad51-183dce98f116)
![keyward_search](https://github.com/sulim1222/semiproject/assets/169763082/bafc7989-abc8-49ae-95b9-3a7d2aeb8404)
- location과 keyword를 통해 부분 검색어에 포함한 데이터를 출력
<br>
4. 신규 데이터
![new](https://github.com/sulim1222/semiproject/assets/169763082/f6cec911-d5d1-488c-87ac-88a6aad3582c)
<br>
5. 기존 데이터 업데이트
![update](https://github.com/sulim1222/semiproject/assets/169763082/81c8aa9b-5aeb-4527-8c69-0045e95cd6e1)

- JSP를 통해 각 필드 값을 넣어
- AJAX를 사용하여 비동기적으로 서버로부터 예약 데이터를 가져오는데 이때 데이터 교환의 효율성과 편의성을 위해 JSON 형식을 활용하였습니다. 서버 측에서는 예약 정보를 JSON 형태로 응답하도록 구현하였고, 클라이언트 측에서는 AJAX 요청을 통해 이 JSON 데이터를 받아와 동적으로 폼을 채우는 방식으로 구현하였습니다.
<br>
6. 월별, 지역별 매출
![sales_by month](https://github.com/sulim1222/semiproject/assets/169763082/3e523ae9-bf7e-483e-a71b-4d116d9d1262)
![sales_by location](https://github.com/sulim1222/semiproject/assets/169763082/05da14ce-9410-4f89-8593-cec2751f9efe)

Chart.js를 활용하여 지역별 월별 매출을 시각화하였습니다.
시각적으로 구분이 용이하게 막대 그래프를 이용하였으며 사용자가 차트의 특정 지점에 마우스를 올리면 해당 월의 정확한 매출 금액을 툴팁으로 표시하도록 구현하였습니다.




## 🔖 보완점 및 추가 기능 계획
보완점
1. 첫 프로젝트다 보니 코드의 복잡도와 자바스크립트를 이용한 기능들의 가독성이 떨어지는 부분에 대해서 보완이 필요
2. 디비 설계 미흡으로 인해 프로젝트 중간에 컬럼 및 테이블이 추가되는 부분이 있었는데 프로젝트 시작시 디비 설계와 UI의 중요성을 파악
3. 처음 접해보는 chart.js 라이브러리에 데이터 적용이 어려웠으나 공식 문서와 예제 코드를 철저히 학습하여 라이브러리의 기본 구조와 사용법을 파악

추가 기능 계획
1. 취소된 예약건들에 대해 리스트 생성하고 체크박스를 통해 다중 선택 취소 기능을 구현할 계획
2. 해당 일자의 객실 타입별 객실 재고 관리가 되는 기능을 구현할 계획
3. SPRING을 이용하여 백엔드 로직을 개선하고, RESTFUL API를 구현하여 프론트엔드와의 통신을 더욱 효율적으로 만들 계획
4. 보안 강화를 위해 Spring Security를 도입하여 사용자 인증 및 권한 관리 시스템을 구현할 계획

