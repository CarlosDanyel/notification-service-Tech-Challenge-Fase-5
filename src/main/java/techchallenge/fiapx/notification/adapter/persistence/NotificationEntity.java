package techchallenge.fiapx.notification.adapter.persistence;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id public UUID id;
    @Column(name = "event_id", nullable = false, unique = true) public UUID eventId;
    @Column(name = "video_id", nullable = false) public UUID videoId;
    @Column(nullable = false) public String email;
    @Column(nullable = false) public String status;
    @Column(name = "sent_at") public Instant sentAt;
    @Column(name = "created_at", nullable = false) public Instant createdAt;
    @Column(name = "updated_at", nullable = false) public Instant updatedAt;
    protected NotificationEntity() {}
    public NotificationEntity(UUID eventId, UUID videoId, String email) {
        id = UUID.randomUUID(); this.eventId = eventId; this.videoId = videoId;
        this.email = email; status = "PENDING";
    }
    @PrePersist void create() { createdAt = Instant.now(); updatedAt = createdAt; }
    @PreUpdate void update() { updatedAt = Instant.now(); }
}
