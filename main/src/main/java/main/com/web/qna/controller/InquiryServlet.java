package main.com.web.qna.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.qna.dto.Inquiry;
import main.com.web.qna.service.InquiryService;
import main.com.web.member.dto.Member;

@WebServlet("/qna/submitInquiry")
public class InquiryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InquiryServlet() {
        super();
    }

    // GET 메소드 추가
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청을 inquiry.jsp로 리다이렉트
        request.getRequestDispatcher("/WEB-INF/views/qna/inquiry.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Member loggedInUser = (Member) request.getSession().getAttribute("member");
        if (loggedInUser == null) {
            System.out.println("User not logged in, redirecting to login page.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String inquiryType = request.getParameter("category");
        String title = request.getParameter("title");
        String content = request.getParameter("inquiry");
        int MemberNo = loggedInUser.getMemberNo();

        // 현재 날짜와 시간을 구하여 설정
        Date inquiryDate = new Date(System.currentTimeMillis());
        Time inquiryTime = new Time(System.currentTimeMillis());

        Inquiry inquiry = Inquiry.builder()
                .inquiryType(inquiryType)
                .title(title)
                .content(content)
                .inquiryDate(inquiryDate)
                .inquiryTime(inquiryTime)
                .MemberNo(MemberNo)
                .build();

        System.out.println("Submitting inquiry: " + inquiry);

        InquiryService service = new InquiryService();
        int result = service.saveInquiry(inquiry);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/qna/success");
        } else {
            response.sendRedirect(request.getContextPath() + "/qna/failure");
        }
    }
}
