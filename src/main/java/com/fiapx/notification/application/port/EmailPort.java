package com.fiapx.notification.application.port;
import com.fiapx.notification.domain.FailureNotification;
public interface EmailPort {
    void sendFailure(FailureNotification event);
}
