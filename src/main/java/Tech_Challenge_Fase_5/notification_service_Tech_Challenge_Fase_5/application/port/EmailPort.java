package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.port;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain.FailureNotification;
public interface EmailPort {
    void sendFailure(FailureNotification event);
}
