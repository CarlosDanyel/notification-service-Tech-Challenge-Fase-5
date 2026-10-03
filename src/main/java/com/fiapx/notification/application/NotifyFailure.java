package com.fiapx.notification.application;
import com.fiapx.notification.application.port.DeliveryStore;
import com.fiapx.notification.application.port.EmailPort;
import com.fiapx.notification.domain.FailureNotification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class NotifyFailure {
    private final DeliveryStore deliveries;
    private final EmailPort email;
    public NotifyFailure(DeliveryStore deliveries, EmailPort email) {
        this.deliveries = deliveries; this.email = email;
    }
    @Transactional
    public void handle(FailureNotification event) {
        if (deliveries.wasSent(event.eventId())) return;
        email.sendFailure(event);
        deliveries.recordSent(event);
    }
}
