<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/onetoone.css"><!-- 각 파일에 위치하는값 설정 --> 

  <div id="mainTitle" class="main-title">
            <h1>Q&a</h1>
        </div>
    </header>
    <section class="main-section">
    
        <div class="inquiry-container">
            <h1>문의하기</h1>
            <form id="inquiryForm">
                <table class="inquiry-table">
                    <tbody>
                        <tr>
                            <th>분류</th>
                            <td>
                                <div class="dropdown">
                                    <input type="text" name="category" id="categoryInput" readonly placeholder="카테고리를 설정해주세요." onclick="toggleDropdown()" required>
                                    <div class="dropdown-icon" onclick="toggleDropdown()">▼</div>
                                    <div id="dropdownMenu" class="dropdown-menu">
                                        <div onclick="selectCategory('PAY')">PAY</div>
                                        <div onclick="selectCategory('RESERVATION')">RESERVATION</div> <!--필요없을시 삭제!-->
                                        <div onclick="selectCategory('ETC')">ETC</div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" required></td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td><input type="text" name="writer" required></td>
                        </tr>
                        <tr>
                            <th>비밀번호</th>
                            <td><input type="password" name="password" required></td>
                        </tr>
                        <tr>
                            <th>휴대폰</th>
                            <td><input type="tel" name="phone" required></td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td><input type="email" name="email" required></td>
                        </tr>
                        <tr>
                            <th>문의사항</th>
                            <td><textarea name="inquiry" rows="6" required></textarea></td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td id="fileInputs">
                                <div class="file-input">
                                    <input type="file" name="addFile">
                                    <button type="button" onclick="addFileInput()">추가</button>
                                    <button type="button" onclick="removeFileInput(this)">삭제</button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="privacy-policy">
                    <input type="checkbox" name="agree" id="agree" required>
                    <label for="agree">개인정보취급방침 내용에 동의합니다.</label>
                </div>
                <div class="privacy-policy-content">
                    <h2>개인정보취급방침</h2>
                    <p>
                        1. 총칙<br>
                        METHOD HOTEL은 이용자들의 개인정보보호를 매우 중요시하며, 회사의 서비스를 이용함과 동시에 온라인상에서 METHOD에 제공한 개인정보가 보호받을 수 있도록 최선을 다하고 있습니다.
                    </p>
                </div>
                <div class="submit-button">
                    <button type="button" onclick="validateForm()">등록</button>
                </div>
            </form>
        </div>
    </section>


 <script src="<%=request.getContextPath()%>/js/onetoone.js"></script> <!-- javascript 내용 -->

<!-- </script> -->
<%@ include file="../common/footer.jsp"%>
