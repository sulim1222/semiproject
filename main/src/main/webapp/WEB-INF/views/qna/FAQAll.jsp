<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/faq.css"> 


 <section class="main-section">
        <div class="main-content">
             <!-- Enjoy 서브 메뉴 모음  -->
             <div class="sub-content">
                <ul>
                	<li><a href="<%=request.getContextPath() %>/qna/faqAll">ALL</a></li>
                    <li><a href="<%=request.getContextPath() %>/qna/faqPay">PAY</a></li>
                    <li><a href="<%=request.getContextPath() %>/qna/faqEtc">ETC</a></li>
                </ul>
            </div>
            <table class="faq-table">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>작성시간</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="faq-question">
                        <td>10</td>
                        <td>PAY</td>
                        <td>회원가입 OR 탈퇴는 어떻게 하나요</td>
                        <td>2024.05.05</td>
                    </tr>
                    <tr class="faq-answer">
                        <td colspan="4">회원가입 OR 탈퇴는 다음과 같은 절차를 따릅니다...</td>
                    </tr>
                    <tr class="faq-question">
                        <td>09</td>
                        <td>PAY</td>
                        <td>회원가입 OR 탈퇴는 어떻게 하나요</td>
                        <td>2024.05.05</td>
                    </tr>
                    <tr class="faq-answer">
                        <td colspan="4">회원가입 OR 탈퇴는 다음과 같은 절차를 따릅니다...</td>
                    </tr>
                    <tr class="faq-question">
                        <td>08</td>
                        <td>PAY</td>
                        <td>회원가입 OR 탈퇴는 어떻게 하나요</td>
                        <td>2024.05.05</td>
                    </tr>
                    <tr class="faq-answer">
                        <td colspan="4">회원가입 OR 탈퇴는 다음과 같은 절차를 따릅니다...</td>
                    </tr>
                    <tr class="faq-question">
                        <td>07</td>
                        <td>PAY</td>
                        <td>회원가입 OR 탈퇴는 어떻게 하나요</td>
                        <td>2024.05.05</td>
                    </tr>
                    <tr class="faq-answer">
                        <td colspan="4">회원가입 OR 탈퇴는 다음과 같은 절차를 따릅니다...</td>
                    </tr>
                    <tr class="faq-question">
                        <td>06</td>
                        <td>PAY</td>
                        <td>회원가입 OR 탈퇴는 어떻게 하나요</td>
                        <td>2024.05.05</td>
                    </tr>
                    <tr class="faq-answer">
                        <td colspan="4">회원가입 OR 탈퇴는 다음과 같은 절차를 따릅니다...</td>
                    </tr>
                    <tr class="faq-question">
                        <td>05</td>
                        <td>PAY</td>
                        <td>회원가입 OR 탈퇴는 어떻게 하나요</td>
                        <td>2024.05.05</td>
                    </tr>
                    <tr class="faq-answer">
                        <td colspan="4">회원가입 OR 탈퇴는 다음과 같은 절차를 따릅니다...</td>
                    </tr>
                </tbody>
            </table>



            
            <!-- 페이징 번호 -->
            <!-- 페이징 처리해야함, 임시로 작성  -->
            <div class="paging">
                <span>1</span>
                <span>2</span>
                <span>3</span>
                <span>4</span>
                <span>5</span>
            </div>

        </div>



    </section>

<script src="<%=request.getContextPath()%>/js/faq.js"></script> <!-- javascript 내용 -->
<%@ include file="../common/footer.jsp"%>
