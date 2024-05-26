package main.com.web.admin.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.dao.AdminFAQService;
import main.com.web.qna.dto.FAQ;

@WebServlet("/admin/faqList")
public class AdminFAQListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminFAQListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cPage = 0;
        try {
            cPage = Integer.parseInt(request.getParameter("cPage"));
        } catch (NumberFormatException e) {
            cPage = 1;
        }
        int numPerPage = 10;
        
        String searchCategory = request.getParameter("searchCategory");
        if (searchCategory == null) {
            searchCategory = "ALL";
        }
        String searchKeyword = request.getParameter("searchKeyword");
        
        AdminFAQService service = new AdminFAQService();
        List<FAQ> faqs = service.selectFAQAll(cPage, numPerPage, searchCategory, searchKeyword);
        
        int totalData = service.selectFAQAllCount(searchCategory, searchKeyword);
        int totalPage = (int) Math.ceil((double) totalData / numPerPage);
        int pageBarSize = 5;
        int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
        int pageEnd = pageNo + pageBarSize - 1;

        StringBuilder pageBar = new StringBuilder();
        if (pageNo == 1) {
            pageBar.append("<span>[이전]</span>");
        } else {
            pageBar.append("<a href='").append(request.getRequestURI())
                   .append("?cPage=").append(pageNo - 1).append("&numPerPage=").append(numPerPage)
                   .append("&searchCategory=").append(searchCategory)
                   .append("&searchKeyword=").append(searchKeyword).append("'>[이전]</a>");
        }

        while (!(pageNo > pageEnd || pageNo > totalPage)) {
            if (pageNo == cPage) {
                pageBar.append("<span>").append(pageNo).append("</span>");
            } else {
                pageBar.append("<a href='").append(request.getRequestURI())
                       .append("?cPage=").append(pageNo).append("&numPerPage=").append(numPerPage)
                       .append("&searchCategory=").append(searchCategory)
                       .append("&searchKeyword=").append(searchKeyword).append("'>").append(pageNo).append("</a>");
            }
            pageNo++;
        }

        if (pageNo > totalPage) {
            pageBar.append("<span>[다음]</span>");
        } else {
            pageBar.append("<a href='").append(request.getRequestURI())
                   .append("?cPage=").append(pageNo).append("&numPerPage=").append(numPerPage)
                   .append("&searchCategory=").append(searchCategory)
                   .append("&searchKeyword=").append(searchKeyword).append("'>[다음]</a>");
        }

        request.setAttribute("pageBar", pageBar.toString());
        request.setAttribute("faqs", faqs);
        request.getRequestDispatcher("/WEB-INF/views/admin/AdminFAQList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
