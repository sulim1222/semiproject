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
                <li>Reservations</li>
                <li><a href="<%=request.getContextPath()%>/admin/FAQList?location=서울">Seoul</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/FAQList?location=부산">Busan</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/FAQList?location=제주">Jeju</a></li>
            </ul>
        </nav>
    </aside>

    <main class="main">
        <span id="title">
        <% if ("서울".equals(location)) { %>
            Seoul FAQ List
        <% } else if ("부산".equals(location)) { %>
            Busan FAQ List
        <% } else if ("제주".equals(location)) { %>
            Jeju FAQ List
        <% } else { %>
            FAQ List
        <% } %>
        </span>
        <div class="separator"></div>

        <div class="container1">
            <select id="searchCategory" style="width: 100px; height: 25px;">
               <option value="ALL" <%=searchCategory != null && searchCategory.equals("All") ? "selected" : ""%>>ALL</option>
                <option value="PAY" <%=searchCategory != null && searchCategory.equals("PAY") ? "selected" : ""%>>PAY</option>
                <option value="ETC" <%=searchCategory != null && searchCategory.equals("ETC") ? "selected" : ""%>>ETC</option>
            </select>

            <div id="searchALL" style="display:none;">
                <form action="<%=request.getContextPath()%>/admin/FAQList">
                    <input type="hidden" name="searchCategory" value="ALL">
                    <input type="text" name="searchKeyword" size="25" placeholder="단어를 입력하세요" value="<%= searchKeyword != null ? searchKeyword : "" %>">
                    <button type="submit">검색</button>
                </form>
            </div>

            <div id="search-PAY" style="display:none;">
                <form action="<%=request.getContextPath()%>/admin/FAQList">
                    <input type="hidden" name="searchCategory" value="PAY">
                    <input type="text" name="searchKeyword" size="25" placeholder="단어를 입력하세요" value="<%= searchKeyword != null ? searchKeyword : "" %>">
                    <button type="submit">검색</button>
                </form>
            </div>

            <div id="search-ETC" style="display:none;">
                <form action="<%=request.getContextPath()%>/admin/FAQList">
                    <input type="hidden" name="searchCategory" value="ETC">
                    <input type="text" name="searchKeyword" size="25" placeholder="단어를 입력하세요" value="<%= searchKeyword != null ? searchKeyword : "" %>">
                    <button type="submit">검색</button>
                </form>
            </div>

            <button type="button" id="insertNewFAQ" onclick="location.href='<%=request.getContextPath()%>/admin/AdminFAQInsert.jsp'">신규등록</button>
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
                                <button type="button" onclick="location.href='<%=request.getContextPath()%>/admin/AdminFAQUpdate.jsp?faqAllNo=<%= f.getFaqAllNo() %>'">수정</button>
                                <button type="button" onclick="deleteFAQ(<%= f.getFaqAllNo() %>)">삭제</button>
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
        $("#search" + type).show();
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
        border: 1px solid black;
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
        background-color: lightgray;
        padding: 10px;
        justify-content: center;
        height: 700px;
        border: 1px solid black;
    }

    .aside ul {
        list-style-type: none;
        padding: 0;
        text-align: center;
        margin-top: 50px;
    }

    .aside ul li:nth-child(1) {
        border-bottom: 2px solid black;
        padding-bottom: 30px;
    }

    .aside ul li {
        margin-bottom: 30px;
        font-weight: bold;
        font-size: 20px;
        letter-spacing: 0.1em;
    }

    .aside ul li a {
        letter-spacing: 0.1em;
        text-decoration: none;
        color: black;
        display: block;
        line-height: 1.5;
    }

    .aside ul li a:hover {
        transition: 1s;
        transform: scale(1.2);
    }

    .main {
        width: 80%;
        margin-left: 3%;
        margin-top: 10px;
        display: flex;
        justify-content: space-between;
        padding: 10px;
        border: 1px solid black;
        background-color: lightgray;
        display: block;
    }

    #title {
        font-size: 30px;
        letter-spacing: 0.05em;
        font-weight: bolder;
    }

    .separator {
        border-bottom: 2px solid black;
    }

    .container1 {
        display: flex;
        align-items: center;
        padding-top: 10px;
    }

    .searchInput {
        width: 200px;
        padding: 5px;
    }

    .container2 {
        margin-bottom: 30px;
    }

    .FAQList {
        width: 100%;
        border-collapse: collapse;
    }

    .FAQList th,
    .FAQList td {
        border: 1px solid black;
        padding: 10px;
        text-align: center;
    }

    #inputnewFAQ {
        margin-left: auto;
    }

    div[id^='search-'] {
        display: none;
    }

    .container1 input {
        height: 20px;
    }
</style>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
