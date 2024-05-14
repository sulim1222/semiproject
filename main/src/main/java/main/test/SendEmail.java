package main.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    final String ENCODING = "UTF-8";
    final String PORT = "587";
    final String SMTPHOST = "smtp.naver.com";
    final String TO = "leeae098@naver.com";
    final String USERNAME = "gdj79@naver.com";
    final String PASSWORD = "gdj79BSLOVE!!";
    
    /**
     * Session값 셋팅
     * @param props
     * @return
     */
    public Session setting() {
        Properties props = new Properties();
        
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTPHOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("","");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        return session;
    }
    
    /**
     * 메시지 세팅 후 메일 전송
     * @param session
     * @param title
     * @param content
     */
    public void goMail(Session session, String title, String content) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(USERNAME, "관리자", ENCODING));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            msg.setSubject(title);
            msg.setContent(content, "text/html; charset=utf-8");
            Transport.send(msg);
            System.out.println("메일 보내기 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("메일 보내기 실패");
        }
    }
}
