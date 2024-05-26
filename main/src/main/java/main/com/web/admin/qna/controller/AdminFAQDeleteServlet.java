package main.com.web.admin.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.dao.AdminFAQService;

@WebServlet("/admin/FAQDelete")
public class AdminFAQDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int faqAllNo = Integer.parseInt(request.getParameter("faqAllNo"));

        AdminFAQService service = new AdminFAQService();
        int result = service.deleteFAQ(faqAllNo);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/admin/faqList");
        } else {
            request.setAttribute("message", "FAQ 삭제에 실패했습니다.");
            request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
        }
    }
}
