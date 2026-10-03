package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.adapter.persistence;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.port.DeliveryStore;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain.FailureNotification;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Component;
@Component
public class JpaDeliveryStore implements DeliveryStore {
    private final NotificationRepository repository;
    public JpaDeliveryStore(NotificationRepository repository) { this.repository = repository; }
    public boolean wasSent(UUID eventId) {
        return repository.findByEventId(eventId).map(n -> "SENT".equals(n.status)).orElse(false);
    }
    public void recordSent(FailureNotification event) {
        var notification = repository.findByEventId(event.eventId())
            .orElseGet(() -> new NotificationEntity(event.eventId(), event.videoId(), event.email()));
        notification.status = "SENT";
        notification.sentAt = Instant.now();
        repository.save(notification);
    }
}
