package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.adapter.persistence;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    Optional<NotificationEntity> findByEventId(UUID eventId);
}
