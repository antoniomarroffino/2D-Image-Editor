package ch.supsi.imageEditor.backend.application.observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class NotificationServiceTest {
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
        Assertions.assertEquals(notificationService1, notificationService2);
    }
}