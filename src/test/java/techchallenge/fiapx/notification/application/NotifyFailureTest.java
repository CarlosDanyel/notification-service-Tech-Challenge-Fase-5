package techchallenge.fiapx.notification.application;
import techchallenge.fiapx.notification.application.port.DeliveryStore;
import techchallenge.fiapx.notification.application.port.EmailPort;
import techchallenge.fiapx.notification.domain.FailureNotification;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
class NotifyFailureTest {
    @Test void alreadyDeliveredEventDoesNotSendAgain() {
        var store = mock(DeliveryStore.class);
        var email = mock(EmailPort.class);
        var event = new FailureNotification(UUID.randomUUID(), UUID.randomUUID(), "a@example.com", "Invalid video");
        when(store.wasSent(event.eventId())).thenReturn(true);
        new NotifyFailure(store, email).handle(event);
        verifyNoInteractions(email);
        verify(store, never()).recordSent(any());
    }
    @Test void failureSendsEmailAndRecordsDelivery() {
        var store = mock(DeliveryStore.class);
        var email = mock(EmailPort.class);
        var event = new FailureNotification(UUID.randomUUID(), UUID.randomUUID(), "a@example.com", "Invalid video");
        new NotifyFailure(store, email).handle(event);
        verify(email).sendFailure(event);
        verify(store).recordSent(event);
    }
}
