package Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.adapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.application.NotifyFailure;
import Tech_Challenge_Fase_5.notification_service_Tech_Challenge_Fase_5.domain.FailureNotification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class FailureConsumer {
    private final NotifyFailure notifications;
    private final ObjectMapper mapper;
    public FailureConsumer(NotifyFailure notifications, ObjectMapper mapper) {
        this.notifications = notifications; this.mapper = mapper;
    }
    @RabbitListener(queues = "video.notifications")
    public void receive(String payload) throws Exception {
        notifications.handle(mapper.readValue(payload, FailureNotification.class));
    }
}
