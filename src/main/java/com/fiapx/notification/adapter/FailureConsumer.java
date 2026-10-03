package com.fiapx.notification.adapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiapx.notification.application.NotifyFailure;
import com.fiapx.notification.domain.FailureNotification;
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
