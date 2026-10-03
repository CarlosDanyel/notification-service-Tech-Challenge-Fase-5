package techchallenge.fiapx.notification.adapter;
import techchallenge.fiapx.notification.application.port.EmailPort;
import techchallenge.fiapx.notification.domain.FailureNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class SmtpEmailSender implements EmailPort {
    private final JavaMailSender mail;
    private final String from;
    public SmtpEmailSender(JavaMailSender mail, @Value("${notifications.from}") String from) {
        this.mail = mail; this.from = from;
    }
    public void sendFailure(FailureNotification event) {
        var message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(event.email());
        message.setSubject("FIAP X: video processing failed");
        message.setText("Video " + event.videoId() + " could not be processed. Reason: " + event.reason());
        mail.send(message);
    }
}
