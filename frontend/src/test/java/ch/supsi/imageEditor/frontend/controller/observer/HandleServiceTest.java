package ch.supsi.imageEditor.frontend.controller.observer;

import ch.supsi.imageEditor.frontend.adapter.Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Consumer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class HandleServiceTest {
    private HandleService handleService;

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

    @Test
    public void testSubscribe() {
        this.handleService = HandleService.getInstance();
        Consumer<Component> mockConsumer = Mockito.mock(Consumer.class);
        handleService.subscribe(EventOnApplication.CHANGE_LANGUAGE, mockConsumer);
        Assertions.assertEquals(mockConsumer, handleService.getSubscribers().get(EventOnApplication.CHANGE_LANGUAGE));
    }

    @Test
    void testUnsubscribe() {
        Consumer<Component> mockConsumer = Mockito.mock(Consumer.class);
        this.handleService = HandleService.getInstance();
        handleService.subscribe(EventOnApplication.CHANGE_LANGUAGE, mockConsumer);
        handleService.unsubscribe(EventOnApplication.CHANGE_LANGUAGE, mockConsumer);
        Assertions.assertNull(handleService.getSubscribers().get(EventOnApplication.CHANGE_LANGUAGE));
    }

    @Test
    void testNotify() {
        Consumer<Component> mockConsumer = Mockito.mock(Consumer.class);
        Component mockComponent = Mockito.mock(Component.class);
        this.handleService = HandleService.getInstance();
        handleService.subscribe(EventOnApplication.CHANGE_LANGUAGE, mockConsumer);
        handleService.notify(EventOnApplication.CHANGE_LANGUAGE, mockComponent);
        verify(mockConsumer, times(1)).accept(mockComponent);
    }
}