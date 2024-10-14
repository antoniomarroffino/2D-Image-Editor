package ch.supsi.imageEditor.frontend.controller.observer;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import java.util.*;
import java.util.function.Consumer;

public class HandleService implements HandleServiceInterface {
    private static HandleService instance = null;
    private final Map<EventOnApplication, Consumer<MenuItem>> subscribers;

    private HandleService() {
        this.subscribers = new HashMap<>();
        Arrays.stream(EventOnApplication.values()).forEach((event) -> this.subscribers.put(event, null));
    }

    public static HandleService getInstance() {
        return instance == null? instance = new HandleService() : instance;
    }

    @Override
    public void subscribe(EventOnApplication eventType, Consumer<MenuItem> consumer) {
        this.subscribers.put(eventType, consumer);
    }

    @Override
    public void unsubscribe(EventOnApplication eventType, Consumer<MenuItem> consumer) {
        this.subscribers.remove(eventType, consumer);
    }

    @Override
    public void notify(EventOnApplication eventType, MenuItem event) {
        Consumer<MenuItem> handler = this.subscribers.get(eventType);
        if(handler != null) {
            handler.accept(event);
        }
    }
}
