package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.port;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain.FailureNotification;
import java.util.UUID;
public interface DeliveryStore {
    boolean wasSent(UUID eventId);
    void recordSent(FailureNotification event);
}
