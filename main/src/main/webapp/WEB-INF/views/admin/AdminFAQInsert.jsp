<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>

<section id="insert-container">
    <h2>FAQ 신규 등록</h2>
    <form action="<%=request.getContextPath()%>/admin/insertFAQ" method="post">
        <table>
            <tr>
                <th>카테고리</th>
                <td>
                    <select id="faqCategory" name="faqCategory">
                        <option value="PAY">PAY</option>
                        <option value="ETC">ETC</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td>
                    <input type="text" id="faqTitle" name="faqTitle" required>
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea id="faqContent" name="faqContent" required></textarea>
                </td>
            </tr>
            <tr>
                <th>위치</th>
                <td>
                    <input type="text" id="location" name="location" value="제주" required>
                </td>
            </tr>
        </table>
        <input type="submit" value="등록">
    </form>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<style>
    body {
        background-color: lightgray;
        margin: 0;
        padding: 0;
    }
    #insert-container {
        width: 60%;
        margin: 2rem auto;
        background-color: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }
    h2 {
        background-color: lightgray;
        margin: 10px;
        padding: 1rem;
        text-align: center;
    }
    form {
        padding: 1rem;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 30px;
    }
    th, td {
        padding: 0.75rem;
        text-align: left;
        background-color: lightgray;
        border: 1px solid black;
        text-align: center;
    }
    td input[type="text"],
    td select, 
    textarea {
        width: 90%;
        border: 1px solid lightgray;
        border-radius: 10px;
        height: 30px;
        text-align: center;
    }
    textarea {
        resize: none;
        height: 100px;
    }
    input[type="submit"] {
        display: block;
        width: 100%;
        padding: 1rem;
        background-color: lightgray;
        border: none;
        color: black;
        border-radius: 4px;
        font-size: 1rem;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: lightblue;
    }
</style>
