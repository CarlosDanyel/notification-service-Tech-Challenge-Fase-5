package techchallenge.fiapx.notification.domain;
import java.util.UUID;
public record FailureNotification(UUID eventId, UUID videoId, String email, String reason) {}
