package sys.crh.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class LVMMailSender {
	private String to;
	private String from;
	
	private String smtpServer;
	private String username;
	private String password;
	
	private String subject;
	private String content;
	
	List<String> attachments = new ArrayList<String>();
	
	public LVMMailSender() {
		Properties props = new Properties();
		try {
			props.load(LVMMailSender.class.getResourceAsStream("./mail.properties"));
			this.setSmtpServer(props.getProperty("mail.smtp.host"));
			this.setUsername(props.getProperty("mail.username"));
			this.setPassword(props.getProperty("mail.password"));
			this.setTo(props.getProperty("mail.to"));
			this.setFrom(props.getProperty("mail.from"));
			this.setSubject(props.getProperty("mail.subject"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LVMMailSender(String to, String from, String smtpServer,
			String username, String password, String subject, String content) {
		super();
		this.to = to;
		this.from = from;
		this.smtpServer = smtpServer;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void attachfile(String fname){
		attachments.add(fname);
	}
	
	public String printDetails(){
		return "Server: "+this.getSmtpServer()+"\nUsername: "+this.getUsername()+"\nPassword: "+this.getPassword();
	}
	
	public boolean send(){
		Properties props = new Properties();
		
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
        props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
        props.setProperty("mail.smtp.port", "465"); 
        props.setProperty("mail.smtp.socketFactory.port", "465");
		
		Session session = Session.getDefaultInstance(props, new Authenticator(){
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
		});
		
		try{
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(this.getFrom()));
			
			InternetAddress[] addresses = {new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO, addresses);
			
			msg.setSubject(this.getSubject());
			
			Multipart mp = new MimeMultipart();
			
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setText(this.getContent());
			
			mp.addBodyPart(mbpContent);
			
			attachments.clear();
			
			msg.setContent(mp);
			
			msg.setSentDate(new Date());
			
			Transport.send(msg);
			
			
		}catch(MessagingException mex){
			mex.printStackTrace();
			return false;
		}
		
		return true;
		
	}
}
