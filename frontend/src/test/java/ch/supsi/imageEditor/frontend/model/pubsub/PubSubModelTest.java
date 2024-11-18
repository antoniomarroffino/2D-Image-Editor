package ch.supsi.imageEditor.frontend.model.pubsub;

import ch.supsi.imageEditor.backend.application.language.LanguageController;
import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PubSubModelTest {
    private PubSubModel pubSubModel;

    @BeforeEach
    public void beforeEach() {
        PubSubModel.instance = null;
    }

    @Test
    public void constructor() {
        this.pubSubModel = new PubSubModel();
        Assertions.assertNotNull(this.pubSubModel);
        Assertions.assertNotNull(this.pubSubModel.getNotificationService());
    }

    @Test
    public void instance() {
        this.pubSubModel = PubSubModel.getInstance();
        Assertions.assertNotNull(this.pubSubModel);
        Assertions.assertNotNull(PubSubModel.instance);
        Assertions.assertNotNull(this.pubSubModel.getNotificationService());
    }

    @Test
    public void checkSingleton() {
        PubSubModel pubSubModel1 = PubSubModel.getInstance();
        PubSubModel pubSubModel2 = PubSubModel.getInstance();
        Assertions.assertEquals(pubSubModel1, pubSubModel2);
    }

    @Test
    void testSubscribe() {
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);
        EventListener listener = Mockito.mock(EventListener.class);
        try (MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            this.pubSubModel = PubSubModel.getInstance();
            this.pubSubModel.subscribe(EventType.OPEN_IMAGE, listener);
            verify(mockNotificationService, times(1)).subscribe(EventType.OPEN_IMAGE, listener);
        }
    }

    @Test
    void testUnsubscribe() {
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);
        EventListener listener = Mockito.mock(EventListener.class);
        try (MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            this.pubSubModel = PubSubModel.getInstance();
            pubSubModel.unsubscribe(EventType.OPEN_IMAGE, listener);
            verify(mockNotificationService, times(1)).unsubscribe(EventType.OPEN_IMAGE, listener);
        }
    }
}