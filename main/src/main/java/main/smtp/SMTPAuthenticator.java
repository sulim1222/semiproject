package main.smtp;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator  extends Authenticator{
		private final String username;
		private final String password;
		public SMTPAuthenticator(String username,String password) {
			this.username = username;
			this.password=password;
		}
		protected PasswordAuthentication getAuthentication() {
			return new PasswordAuthentication(username, password);	
		}
}
