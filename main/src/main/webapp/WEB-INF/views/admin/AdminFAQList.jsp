<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%@ page import="main.com.web.qna.dto.FAQ" %>

<%
    String searchCategory = request.getParameter("searchCategory");
    String searchKeyword = request.getParameter("searchKeyword");
    List<FAQ> faqs = (List<FAQ>) request.getAttribute("faqs");
    String pageBar = (String) request.getAttribute("pageBar");
    String location = (String) request.getAttribute("location");
%>

<section class="sectionflex">
    <aside class="aside">
        <nav>
            <ul>
                <li>Q&A</li>
                <li><a href="<%=request.getContextPath()%>/admin/FAQList">FAQ</a></li>
                <li><a href="#" onclick="alert('아직 개발 중입니다.'); return false;">Q&A</a></li>
            </ul>
        </nav>
    </aside>

    <main class="main">
        <div id="titleContainer">
            <div id="title">
                <% if ("서울".equals(location)) { %>
                    Seoul FAQ List
                <% } else if ("부산".equals(location)) { %>
                    Busan FAQ List
                <% } else if ("제주".equals(location)) { %>
                    Jeju FAQ List
                <% } else { %>
                    FAQ List
                <% } %>
            </div>
            <div id="btn5">
                <button class="btn5" onclick="location.assign('<%=request.getContextPath()%>/')">
                    <img src="<%=request.getContextPath()%>/imges/admin/homepage.jpg" >
                </button>
            </div>
        </div>
        <div class="separator"></div>

        <div class="container1">
            <select id="searchCategory">
                <option value="ALL" <%=searchCategory != null && searchCategory.equals("ALL") ? "selected" : ""%>>ALL</option>
                <option value="PAY" <%=searchCategory != null && searchCategory.equals("PAY") ? "selected" : ""%>>PAY</option>
                <option value="ETC" <%=searchCategory != null && searchCategory.equals("ETC") ? "selected" : ""%>>ETC</option>
            </select>

            <div id="search-ALL" style="display:none;">
                <form id="searchForm" action="<%=request.getContextPath()%>/admin/FAQList">
                    <input type="hidden" name="searchCategory" value="ALL">
                    <input type="text" name="searchKeyword" placeholder="단어를 입력하세요" value="<%= searchKeyword != null ? searchKeyword : "" %>">
                    <button type="submit" class="btn2">검색</button>
                </form>
            </div>

            <div id="search-PAY" style="display:none;">
                <form id="searchForm" action="<%=request.getContextPath()%>/admin/FAQList">
                    <input type="hidden" name="searchCategory" value="PAY">
                    <input type="text" name="searchKeyword" placeholder="단어를 입력하세요" value="<%= searchKeyword != null ? searchKeyword : "" %>">
                    <button type="submit" class="btn2">검색</button>
                </form>
            </div>

            <div id="search-ETC" style="display:none;">
                <form id="searchForm" action="<%=request.getContextPath()%>/admin/FAQList">
                    <input type="hidden" name="searchCategory" value="ETC">
                    <input type="text" name="searchKeyword" placeholder="단어를 입력하세요" value="<%= searchKeyword != null ? searchKeyword : "" %>">
                    <button type="submit" class="btn2">검색</button>
                </form>
            </div>

            <button type="button" class="btn1" id="insertNewFAQ" onclick="location.assign('<%=request.getContextPath()%>/admin/insertFAQ')">신규등록</button>
        </div>

        <div class="container2">
            <table class="FAQList">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>faqAllNo</th>
                        <th>faqCategory</th>
                        <th>faqTitle</th>
                        <th>faqContent</th>
                        <th>faqDate</th>
                        <th>Update / Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (faqs != null && !faqs.isEmpty()) {
                        for (FAQ f : faqs) { %>
                        <tr>
                            <td><input type="checkbox" class="checkone" onclick="checkOnlyOne(this)"></td>
                            <td><%= f.getFaqAllNo() %></td>
                            <td><%= f.getFaqCategory() %></td>
                            <td><%= f.getFaqTitle() %></td>
                            <td><%= f.getFaqContent() %></td>
                            <td><%= f.getFaqDate() %></td>
                            <td>
                                <input type="button" class="btn" value="수정" onclick="location.assign('<%=request.getContextPath()%>/admin/updateFAQ?faqAllNo=<%= f.getFaqAllNo() %>')">
                                <form action="<%=request.getContextPath()%>/admin/deleteFAQ" method="post" style="display:inline;">
                                    <input type="hidden" name="faqAllNo" value="<%= f.getFaqAllNo() %>">
                                    <input type="submit" class="btn" value="삭제" onclick="return confirm('정말 삭제하시겠습니까?')">
                                </form>
                            </td>
                        </tr>
                    <% }} else { %>
                        <tr>
                            <td colspan="7">검색 결과가 없습니다.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <div id="pageBar">
            <%= pageBar %>
        </div>
    </main>
</section>

<script>
    $(document).ready(function() {
        $("#searchCategory").change();
    });

    $("#searchCategory").change(function(e) {
        const type = e.target.value;
        $(e.target).parent().find("div[id^='search-']").hide();
        $("#search-" + type).show();
    });

    const checkOnlyOne = (checkbox) => {
        const checkboxes = document.querySelectorAll('.checkone');
        checkboxes.forEach((cb) => {
            if (cb !== checkbox) {
                cb.checked = false;
            }
        });
    };

    const deleteFAQ = (faqAllNo) => {
        if (confirm("정말 삭제하시겠습니까?")) {
            location.href = "<%=request.getContextPath()%>/admin/deleteFAQ?faqAllNo=" + faqAllNo;
        }
    };
</script>

<style>
    #pageBar {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #pageBar>* {
        margin-left: 5px;
        margin-right: 5px;
        font-weight: bold;
        text-decoration: none;
        color: black;
    }

    .sectionflex {
        display: flex;
    }

    .aside {
        margin-top: 10px;
        width: 15%;
        background-color: #f7f7f7;
        padding: 20px;
        height: 800px;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        overflow-y: auto;
    }

    .aside ul {
        list-style-type: none;
        padding: 0;
        text-align: center;
        margin-top: 30px;
    }

    .aside ul li:nth-child(1) {
        margin-bottom: 40px;
        font-size: 30px;
        transition: all 0.3s ease-in-out;
        font-weight: bold;
    }

    .aside ul li {
        margin-bottom: 20px;
        font-size: 22px;
        transition: all 0.3s ease-in-out;
    }

    .aside ul li a {
        text-decoration: none;
        color: #333;
        display: block;
        padding: 10px 0;
        border-radius: 5px;
    }

    .aside ul li a:hover {
        background-color: #e0e0e0;
        color: #222;
    }

    .main {
        width: 80%;
        margin-left: 3%;
        margin-top: 10px;
        display: block;
        padding: 10px;
        border: 1px solid black;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    #title {
        font-size: 24px;
        font-weight: bold;
        color: #333;
        margin-bottom: 20px;
    }

    .separator {
        height: 1px;
        background-color: #ccc;
        margin-bottom: 20px;
    }

    .container1 {
        display: flex;
        align-items: center;
        padding-top: 20px;
    }

    .container1 select,
    .container1 input[type="text"],
    .container1 .btn2 {
        margin-right: 10px;
    }

    .container1 select,
    .container1 input[type="text"] {
        width: 100px;
        height: 25px;
    }

    .container2 {
        margin-bottom: 30px;
        margin-top: 40px;
    }

    .FAQList {
        width: 100%;
        border-collapse: collapse;
        border: 1px solid #ddd;
    }

    .FAQList th,
    .FAQList td {
        padding: 13px;
        text-align: center;
    }

    .FAQList th {
        background-color: #f2f2f2;
        color: #333;
        border-bottom: 1px solid #ddd;
    }

    .FAQList td {
        border-bottom: 1px solid #ddd;
    }

    .FAQList tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .FAQList tbody tr:hover {
        background-color: #f2f2f2;
    }

    .btn {
        padding: 3px 5px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn1 {
        margin-left: auto;
        width: 100px;
        height: 34px;
        background-color: #28a745;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .btn2 {
        width: 60px;
        height: 30px;
        background-color: black;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer.
    }

    .btn5 {
        background-color: transparent;
        border: none;
        cursor: pointer;
        padding: 0;
    }

    .btn5 img {
        display: block;
        width: 30px;
        height: 30px;
        object-fit: cover.
    }

    #titleContainer {
        display: flex;
        justify-content: space-between;
        align-items: center.
    }
</style>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
