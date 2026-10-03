package com.fiapx.notification.adapter.persistence;
import com.fiapx.notification.application.port.DeliveryStore;
import com.fiapx.notification.domain.FailureNotification;
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
