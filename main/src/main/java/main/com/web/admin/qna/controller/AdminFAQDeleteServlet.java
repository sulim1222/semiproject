package main.com.web.admin.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.service.AdminFAQService;

@WebServlet("/admin/deleteFAQ")
public class AdminFAQDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminFAQService service = new AdminFAQService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int faqAllNo = Integer.parseInt(request.getParameter("faqAllNo"));

        int result = service.deleteFAQ(faqAllNo);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/FAQList");
        } else {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
