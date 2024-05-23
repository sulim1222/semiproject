<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reservation.css">
 <script src = "<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
    <section class="main-section">
        <div class="main-content">
    <section>
    <div id='calendar'></div>
      <div id="checkdate">
        <div id="checkindate">
          <p id="ck1" name="checkindate">체크인 날짜 : </p>
        </div>
        <div id="checkoutdate">
          <p id="ck2" name="checkoutdate">체크아웃 날짜 : </p>
        </div>
        <div>
          <button id="btn1">확인</button>
          <button id="btn" >초기화</button>
        </div>
      </div>
      <div id="reservation">
        <div id="roomType">
        	<button id="StandardSelect">Standard</button>
        	<button id="DeluxeSelect">Deluxe</button>
        	<button id="SuiteSelect">Suite</button>
        </div>
        <div id="showRoom">
        	<div class = "selectRoom">
        		<img alt="" src="">
        		<div class="roomInfoTop">
        		<div><input type="text" name="request" class="requestInput" placeholder="요청사항"></div>
        		<div class="selectType">
        		<select>
        		<option>==인원==</option>
        		<option value="2">2명</option>
        		<option value="4">4명</option>
        		</select>
        		<select>
        		<option>==자차==</option>
        		<option value="yes">있음</option>
        		<option value="no">없음</option>
        		</select>
        		<select>
        		<option>==침대==</option>
        		<option value="single">single</option>
        		<option value="double">double</option>
        		</select>
        		</div>
        		<div class="roomBottom">
					<p>금액: </p>
					<button>예약</button>
					</div>
        		</div>
        	</div>
        	<div class = "selectRoom">
        		<img alt="" src="">
        		<div class="roomInfoTop">
        		<div><input type="text" name="request" class="requestInput" placeholder="요청사항"></div>
        		<div class="selectType">
        		<select>
        		<option>==인원==</option>
        		<option value="2">2명</option>
        		<option value="4">4명</option>
        		</select>
        		<select>
        		<option>==자차==</option>
        		<option value="yes">있음</option>
        		<option value="no">없음</option>
        		</select>
        		<select>
        		<option>==침대==</option>
        		<option value="single">single</option>
        		<option value="double">double</option>
        		</select>
        		</div>
        		<div class="roomBottom">
					<p>금액: </p>
					<button>예약</button>
					</div>
        		</div>
        	</div>
        	<!-- 묶는거 3개  -->
        	<div class = "selectRoom">
        		<img alt="" src="">
        		<div class="roomInfoTop">
        		<div><input type="text" name="request" class="requestInput" placeholder="요청사항"></div>
        		<div class="selectType">
        		<select>
        		<option>==인원==</option>
        		<option value="2">2명</option>
        		<option value="4">4명</option>
        		</select>
        		<select>
        		<option>==자차==</option>
        		<option value="yes">있음</option>
        		<option value="no">없음</option>
        		</select>
        		<select>
        		<option>==침대==</option>
        		<option value="single">single</option>
        		<option value="double">double</option>
        		</select>
        		</div>
        		<div class="roomBottom">
					<p>금액: </p>
					<button>예약</button>
					</div>
        		</div>
        	</div>
        	<div id="pageBar"></div>
        	<!-- 이부분은 페이징 처리 -->
        </div>
      </div>
    </section>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.11/index.global.min.js'></script>
<script src="<%=request.getContextPath()%>/js/reservation.js"></script>
        </div>
    </section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>