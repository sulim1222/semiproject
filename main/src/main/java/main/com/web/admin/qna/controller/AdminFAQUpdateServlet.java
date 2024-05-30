package main.com.web.admin.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.service.AdminFAQService;
import main.com.web.qna.dto.FAQ;

@WebServlet("/admin/updateFAQ")
public class AdminFAQUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminFAQService service = new AdminFAQService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int faqAllNo = Integer.parseInt(request.getParameter("faqAllNo"));
        FAQ faq = service.getFAQById(faqAllNo);
        request.setAttribute("faq", faq);
        request.getRequestDispatcher("/WEB-INF/views/admin/AdminFAQUpdate.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int faqAllNo = Integer.parseInt(request.getParameter("faqAllNo"));
        String faqCategory = request.getParameter("faqCategory");
        String faqTitle = request.getParameter("faqTitle");
        String faqContent = request.getParameter("faqContent");
        String location = request.getParameter("location");

        FAQ faq = new FAQ(faqAllNo, faqCategory, faqTitle, faqContent, new java.sql.Date(System.currentTimeMillis()), location);

        int result = service.updateFAQ(faq);
        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/FAQList");
        } else {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
