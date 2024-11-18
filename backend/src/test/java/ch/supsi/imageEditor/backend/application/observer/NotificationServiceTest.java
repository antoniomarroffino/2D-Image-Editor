package ch.supsi.imageEditor.backend.application.observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class NotificationServiceTest {

    private NotificationService notificationService;
    @BeforeEach
    public void beforeEach() {
        NotificationService.instance = null;
    }

    @Test
    public void constructor() {
        NotificationService notificationService = new NotificationService();
        Assertions.assertNotNull(notificationService);
        Assertions.assertNotNull(notificationService.getSubscribers());
    }

    @Test
    public void instance() {
        NotificationService notificationService = NotificationService.getInstance();
        Assertions.assertNotNull(notificationService);
        Assertions.assertNotNull(NotificationService.instance);
        Assertions.assertNotNull(notificationService.getSubscribers());
    }

    @Test
    public void checkSingleton() {
        NotificationService notificationService1 = NotificationService.getInstance();
        NotificationService notificationService2 = NotificationService.getInstance();
        assertEquals(notificationService1, notificationService2);
    }

    @Test
    public void subscribeTest() {
        EventListener mockListener = Mockito.mock(EventListener.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);
        try (MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            this.notificationService = NotificationService.getInstance();
            EventType eventType = EventType.CHANGE_LANGUAGE;
            this.notificationService.subscribe(eventType, mockListener);
            verify(mockNotificationService).subscribe(eventType, mockListener);
        }
    }

    @Test
    public void unsubscribeTest() {
        EventListener mockListener = Mockito.mock(EventListener.class);
        NotificationService mockNotificationService = Mockito.mock(NotificationService.class);
        try (MockedStatic<NotificationService> notificationServiceStaticMock = Mockito.mockStatic(NotificationService.class)) {
            notificationServiceStaticMock.when(NotificationService::getInstance).thenReturn(mockNotificationService);
            this.notificationService = NotificationService.getInstance();
            EventType eventType = EventType.CHANGE_LANGUAGE;
            this.notificationService.subscribe(eventType, mockListener);
            this.notificationService.unsubscribe(eventType, mockListener);
            verify(mockNotificationService).unsubscribe(eventType, mockListener);
        }
    }

    @Test
    public void notifyTest() {
        EventListener mockListener = Mockito.mock(EventListener.class);
        notificationService = NotificationService.getInstance();
        notificationService.subscribe(EventType.CHANGE_LANGUAGE, mockListener);
        notificationService.notify(EventType.CHANGE_LANGUAGE);
        verify(mockListener, times(1)).update(EventType.CHANGE_LANGUAGE);
        List<EventListener> listeners = notificationService.getSubscribers().get(EventType.CHANGE_LANGUAGE);
        assertEquals(1, listeners.size(), "Il listener dovrebbe essere presente solo una volta");
    }



}