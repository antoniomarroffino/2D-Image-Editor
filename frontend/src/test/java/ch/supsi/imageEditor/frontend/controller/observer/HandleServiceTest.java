package ch.supsi.imageEditor.frontend.controller.observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandleServiceTest {
    @BeforeEach
    public void beforeEach() {
        HandleService.instance = null;
    }

    @Test
    public void constructor() {
        HandleService handleService = new HandleService();
        Assertions.assertNotNull(handleService);
        Assertions.assertNotNull(handleService.getSubscribers());
    }

    @Test
    public void instance() {
        HandleService handleService = HandleService.getInstance();
        Assertions.assertNotNull(handleService);
        Assertions.assertNotNull(HandleService.instance);
        Assertions.assertNotNull(handleService.getSubscribers());

    }

    @Test
    public void checkSingleton() {
        HandleService handleService1 = HandleService.getInstance();
        HandleService handleService2 = HandleService.getInstance();
        Assertions.assertEquals(handleService1, handleService2);
    }
}