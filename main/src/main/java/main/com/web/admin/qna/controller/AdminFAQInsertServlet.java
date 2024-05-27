package main.com.web.admin.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.service.AdminFAQService;
import main.com.web.qna.dto.FAQ;

@WebServlet("/admin/insertFAQ")
public class AdminFAQInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        request.getRequestDispatcher("/WEB-INF/views/admin/AdminFAQInsert.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String faqCategory = request.getParameter("faqCategory");
        String faqTitle = request.getParameter("faqTitle");
        String faqContent = request.getParameter("faqContent");
        String location = request.getParameter("location");

        FAQ faq = new FAQ(0, faqCategory, faqTitle, faqContent, new java.sql.Date(System.currentTimeMillis()), location);

        AdminFAQService service = new AdminFAQService();
        int result = service.insertNewFAQ(faq);
        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/FAQList");
        } else {
            request.setAttribute("errorMessage", "FAQ 등록 실패");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
