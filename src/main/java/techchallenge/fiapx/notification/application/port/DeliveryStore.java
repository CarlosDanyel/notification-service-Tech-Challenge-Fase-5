package techchallenge.fiapx.notification.application.port;
import techchallenge.fiapx.notification.domain.FailureNotification;
import java.util.UUID;
public interface DeliveryStore {
    boolean wasSent(UUID eventId);
    void recordSent(FailureNotification event);
}
