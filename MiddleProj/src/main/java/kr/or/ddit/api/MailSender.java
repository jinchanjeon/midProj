package kr.or.ddit.api;

import java.util.Properties;



import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSender {

	public static void main(String[] args) {
		
		String title = "9월30일 test 메일";
		String content = "메일 보내기 test 메일 입니다.";
		String user_name = "dditdockertest@gmail.com";
		String password = "uitn wggh yipv prcg"; // xxxxxxx 프로젝트 외 용도로 사용 금지 xxxxxxx
		SendMail sendMail = new SendMail();
		sendMail.goMail(sendMail.setting(new Properties(),user_name,password), title, content);
	}
}



class SendMail {

	final String ENCODING = "UTF-8";
	final String PORT = "465"; // 구글
	final String SMTPHOST = "smtp.gmail.com";
	final String TO = "dditdockertest@gmail.com";
	
	// gmail 465 naver 587
//	final String PORT = "587"; // 네이버
//	final String SMTPHOST = "smtp.naver.com";
//	final String TO = "dditdockertest@naver.com";
	/**
	 * Session값 셋팅
	 * @param props
	 * @return
	 */
		public Session setting(Properties props, String user_name, String password) {
		
		Session session = null;
		
		try {
			
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTPHOST);
			props.put("mail.smtp.port", PORT);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", SMTPHOST);
			props.put("mail.smtp.starttls.required", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");

			props.put("mail.smtp.quit-wait", "false");
			props.put("mail.smtp.socketFactory.port", PORT);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user_name, password);
				}
				
			});
		} catch (Exception e) {		
			System.out.println("Session Setting 실패");
		}
		
		return session;
	}
	
	/**
	 * 메시지 세팅 후 메일 전송
	 * @param session
	 * @param title
	 * @param content
	 */
	public void goMail(Session session, String title, String content) {
		
		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress("dditdockertest@gmail.com"));
			
			
			InternetAddress[] addArray = new InternetAddress[2];
			addArray[0] = new InternetAddress("dditdockertest@naver.com");
			addArray[1] = new InternetAddress("dditdockertest@gmail.com");
			            
			msg.addRecipients(Message.RecipientType.TO, addArray);
			
//			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
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
