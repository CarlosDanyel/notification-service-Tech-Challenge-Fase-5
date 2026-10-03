package techchallenge.fiapx.notification.application.port;
import techchallenge.fiapx.notification.domain.FailureNotification;
public interface EmailPort {
    void sendFailure(FailureNotification event);
}
