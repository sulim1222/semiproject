package main.com.web.admin.qna.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.qna.service.AdminFAQService;
import main.com.web.qna.dto.FAQ;

@WebServlet("/admin/FAQList")
public class AdminFAQListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminFAQListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cPage = 1;
        int numPerpage = 10;

        try {
            cPage = Integer.parseInt(request.getParameter("cPage"));
        } catch (NumberFormatException e) {
            // Use default value
        }

        try {
            numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
        } catch (NumberFormatException e) {
            // Use default value
        }

        String searchCategory = request.getParameter("searchCategory");
        String searchKeyword = request.getParameter("searchKeyword");
        String location = request.getParameter("location");

        AdminFAQService service = new AdminFAQService();
        List<FAQ> faqs = null;
        int totalData = 0;

        if (searchCategory == null || searchCategory.equals("ALL")) {
            faqs = service.selectFAQAll(cPage, numPerpage);
            totalData = service.selectFAQAllCount();
        } else if (searchCategory.equals("PAY")) {
            faqs = service.searchPAY(searchCategory, searchKeyword, location, cPage, numPerpage);
            totalData = service.searchPAYCount(searchCategory, searchKeyword);
        } else if (searchCategory.equals("ETC")) {
            faqs = service.searchETC(searchCategory, searchKeyword, location, cPage, numPerpage);
            totalData = service.searchETCCount(searchCategory, searchKeyword);
        }

        int totalPage = (int) Math.ceil((double) totalData / numPerpage);
        int pageBarSize = 5;
        int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
        int pageEnd = pageNo + pageBarSize - 1;
        
        StringBuilder pageBar = new StringBuilder();
        if (pageNo == 1) {
            pageBar.append("<span>[이전]</span>");
        } else {
            pageBar.append("<a href='").append(request.getRequestURI())
                   .append("?cPage=").append(pageNo - 1).append("'>[이전]</a>");
        }

        while (!(pageNo > pageEnd || pageNo > totalPage)) {
            if (pageNo == cPage) {
                pageBar.append("<span>").append(pageNo).append("</span>");
            } else {
                pageBar.append("<a href='").append(request.getRequestURI())
                       .append("?cPage=").append(pageNo).append("'>").append(pageNo).append("</a>");
            }
            pageNo++;
        }

        if (pageNo > totalPage) {
            pageBar.append("<span>[다음]</span>");
        } else {
            pageBar.append("<a href='").append(request.getRequestURI())
                   .append("?cPage=").append(pageNo).append("'>[다음]</a>");
        }

        request.setAttribute("pageBar", pageBar.toString());
        request.setAttribute("faqs", faqs);
        request.setAttribute("location", location);
        request.getRequestDispatcher("/WEB-INF/views/admin/AdminFAQList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
