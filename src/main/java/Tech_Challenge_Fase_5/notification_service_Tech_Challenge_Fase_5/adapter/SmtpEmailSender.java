package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.adapter;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.port.EmailPort;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain.FailureNotification;
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
