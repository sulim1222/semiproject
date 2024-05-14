package main.com.web.member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/member/sendEmail")
public class SendEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String emailDomain = request.getParameter("emailDomain");
        String recipientEmail = userId + emailDomain;
        String code = request.getParameter("code");
        System.out.println(code);
        // 네이버 SMTP 서버 정보 설정
        final String HOST = "smtp.naver.com";
        final String PORT = "465";
        final String MAIL_ID = "gdj79@naver.com";
        final String MAIL_PW = "gdj79BSLOVE!!";
        try {
            // 수신자 설정
            InternetAddress[] receiverList = new InternetAddress[1];
            receiverList[0] = new InternetAddress(recipientEmail);
            // SMTP 발송 Properties 설정
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", HOST);
			props.put("mail.smtp.port", PORT);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", HOST);
			props.put("mail.smtp.starttls.required", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.quit-wait", "false");
			props.put("mail.smtp.socketFactory.port", PORT);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
            // SMTP Session 생성 
            Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(MAIL_ID, MAIL_PW);
                }
            });
            // Mail 조립
            Message mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress(MAIL_ID));
            mimeMessage.setRecipients(Message.RecipientType.TO, receiverList);
            // 메일 제목
            mimeMessage.setSubject("test");
            // 메일 본문
            mimeMessage.setText("test1");
            // Mail 발송
            Transport.send(mimeMessage);
            System.out.println("이메일 전송 성공!!");
            JSONObject jobj = new JSONObject();
            jobj.put("sendEmail",true); // 결과값을 넘겨 줄수있다. 
            response.setContentType("application/x-json; charset=utf-8"); // 보낼 방식 
        	response.getWriter().print(jobj);	// 보낸방법 
        } catch(Exception e) {
            // 예외 발생 시
            System.out.println("이메일 전송 실패: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().write("이메일 전송 실패: " + e.getMessage());
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
