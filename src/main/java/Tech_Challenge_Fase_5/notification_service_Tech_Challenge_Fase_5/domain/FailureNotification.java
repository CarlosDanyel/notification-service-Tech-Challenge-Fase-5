package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain;
import java.util.UUID;
public record FailureNotification(UUID eventId, UUID videoId, String email, String reason) {}
