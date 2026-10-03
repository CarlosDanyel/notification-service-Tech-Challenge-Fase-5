package techchallenge.fiapx.notification.adapter.persistence;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    Optional<NotificationEntity> findByEventId(UUID eventId);
}
