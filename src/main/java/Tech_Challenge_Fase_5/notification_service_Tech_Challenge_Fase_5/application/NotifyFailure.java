package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.port.DeliveryStore;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.port.EmailPort;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain.FailureNotification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class NotifyFailure {
    private final DeliveryStore deliveries;
    private final EmailPort email;
    public NotifyFailure(DeliveryStore deliveries, EmailPort email) {
        this.deliveries = deliveries; this.email = email;
    }
    @Transactional
    public void handle(FailureNotification event) {
        if (deliveries.wasSent(event.eventId())) return;
        email.sendFailure(event);
        deliveries.recordSent(event);
    }
}
