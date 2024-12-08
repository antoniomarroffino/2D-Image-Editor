package ch.supsi.imageEditor.frontend.controller.observer;

import ch.supsi.imageEditor.frontend.adapter.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class HandleService implements HandleServiceInterface {
    protected static HandleService instance = null;
    private final Map<EventOnApplication, Consumer<Component>> subscribers;

    protected HandleService() {
        this.subscribers = new HashMap<>();
        Arrays.stream(EventOnApplication.values()).forEach((event) -> this.subscribers.put(event, null));
    }

    public static HandleService getInstance() {
        return instance == null ? instance = new HandleService() : instance;
    }

    Map<EventOnApplication, Consumer<Component>> getSubscribers() {
        return this.subscribers;
    }

    @Override
    public void subscribe(EventOnApplication eventType, Consumer<Component> consumer) {
        this.subscribers.put(eventType, consumer);
    }

    @Override
    public void unsubscribe(EventOnApplication eventType, Consumer<Component> consumer) {
        this.subscribers.remove(eventType, consumer);
    }

    @Override
    public void notify(EventOnApplication eventType, Component event) {
        Consumer<Component> handler = this.subscribers.get(eventType);
        if (handler != null) {
            handler.accept(event);
        }
    }
}
