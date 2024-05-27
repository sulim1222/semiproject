package main.com.web.qna.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.com.web.qna.dto.FAQ;
import main.com.web.qna.service.FAQService;

@WebServlet("/qna/FAQAll")
public class FAQListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FAQListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cPage = 1;
        try {
            cPage = Integer.parseInt(request.getParameter("cPage"));
        } catch (NumberFormatException e) {
            // 기본값으로 cPage를 1로 설정합니다.
        }

        int numPerpage = 10;
        try {
            numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
        } catch (NumberFormatException e) {
            // 기본값으로 numPerpage를 10으로 설정합니다.
        }

        FAQService service = new FAQService();
        List<FAQ> faqs = service.selectFAQAll(cPage, numPerpage);
        int totalData = service.selectFAQAllCount();

        int totalPage = (int) Math.ceil((double) totalData / numPerpage);
        int pageBarSize = 10;
        int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
        int pageEnd = pageNo + pageBarSize - 1;

        StringBuffer pageBar = new StringBuffer();
        if (pageNo == 1) {
            pageBar.append("<span>[이전]</span>");
        } else {
            pageBar.append("<a href='" + request.getRequestURL()
                    + "?cPage=" + (pageNo - 1) + "&numPerpage=" + numPerpage + "'>[이전]</a>");
        }

        while (!(pageNo > pageEnd || pageNo > totalPage)) {
            if (pageNo == cPage) {
                pageBar.append("<span>" + pageNo + "</span>");
            } else {
                pageBar.append("<a href='" + request.getRequestURI()
                        + "?cPage=" + pageNo + "&numPerpage=" + numPerpage + "'>" + pageNo + "</a>");
            }
            pageNo++;
        }

        if (pageNo > totalPage) {
            pageBar.append("<span>[다음]</span>");
        } else {
            pageBar.append("<a href='" + request.getRequestURI()
                    + "?cPage=" + pageNo + "&numPerpage=" + numPerpage + "'>[다음]</a>");
        }

        request.setAttribute("pageBar", pageBar.toString());
        request.setAttribute("faqs", faqs);
        request.getRequestDispatcher("/WEB-INF/views/qna/FAQAll.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
