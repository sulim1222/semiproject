package main.com.web.admin.qna.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.dao.AdminFAQService;
import main.com.web.qna.dto.FAQ;

@WebServlet("/admin/faqInsert")
public class AdminFAQInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String faqCategory = request.getParameter("faqCategory");
        String faqTitle = request.getParameter("faqTitle");
        String faqContent = request.getParameter("faqContent");
        String location = request.getParameter("location");

        FAQ faq = FAQ.builder()
                .faqCategory(faqCategory)
                .faqTitle(faqTitle)
                .faqContent(faqContent)
                .faqDate(new java.sql.Date(System.currentTimeMillis()))
                .location(location)
                .build();

        AdminFAQService service = new AdminFAQService();
        int result = service.insertNewFAQ(faq);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/faqList");
        } else {
            request.setAttribute("message", "FAQ 등록에 실패했습니다.");
            request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
        }
    }
}
