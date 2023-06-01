import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/mail")
public class JakartaMail {
  final String MAILSERVER_ADDR = "addr.to.mta.com";
  final String MAILSERVER_PORT = "465"; //465,587;
  final String MAILSERVER_IDENTITY = "your@email.addr.com";
  final String MAILSERVER_LOGIN = "mailserver.login";
  final String MAILSERVER_PASSWD = "mailserver.passwd";
	
  @POST
  @Produces("application/json")
  public String sendMail() {
    sendMail("recipient@example.com", "test", 
        new Date().toString());
    return "{\"done\":true}"; 
  }		  
	  
  class SMTPAuthenticator extends Authenticator {
    public PasswordAuthentication 
    getPasswordAuthentication(){
      return new PasswordAuthentication(MAILSERVER_LOGIN, 
          MAILSERVER_PASSWD);
    } 
  }

  private void sendMail(String toAddr, 
        String theSubject, 
        String theMessage) {
    Properties props = new Properties();

    props.put("mail.smtp.user", MAILSERVER_IDENTITY);
    props.put("mail.smtp.host", MAILSERVER_ADDR);
    props.put("mail.smtp.port", MAILSERVER_PORT);
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.port", 
        MAILSERVER_PORT);
			
    //props.put("mail.smtps.ssl.checkserveridentity", 
    // "false");
    props.put("mail.smtps.ssl.trust",  MAILSERVER_ADDR);
								
    SMTPAuthenticator auth = new SMTPAuthenticator();
    Session session = Session.getInstance(props, auth);
    session.setDebug(false);
			
    MimeMessage msg = new MimeMessage(session);
    try {
      msg.setText(theMessage);
      msg.setSubject(theSubject);
      msg.setFrom(new InternetAddress(
          MAILSERVER_IDENTITY));
      msg.addRecipient(Message.RecipientType.TO, 
          new InternetAddress(toAddr));
				
      // use "smtps" instead of "smtp", to enable SSL
      Transport transport = session.getTransport("smtps");
      transport.connect(MAILSERVER_ADDR, 
          Integer.parseInt(MAILSERVER_PORT), 
          MAILSERVER_LOGIN, MAILSERVER_PASSWD);
      transport.sendMessage(msg, msg.getAllRecipients());
      transport.close();
    } catch (MessagingException e) {
      e.printStackTrace(System.err);
    }
  }
}
