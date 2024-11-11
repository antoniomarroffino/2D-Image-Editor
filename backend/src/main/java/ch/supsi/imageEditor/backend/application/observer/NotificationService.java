package ch.supsi.imageEditor.backend.application.observer;

import java.util.*;

public class NotificationService implements NotificationServiceInterface {
    protected static NotificationService instance = null;

    private final Map<EventType, List<EventListener>> subscribers;

    protected NotificationService() {
        this.subscribers = new HashMap<>();
        Arrays.stream(EventType.values()).forEach(event -> subscribers.put(event, new ArrayList<>()));
    }

    public static NotificationService getInstance() {
        return instance == null ? instance = new NotificationService() : instance;
    }

    Map<EventType, List<EventListener>> getSubscribers() {
        return this.subscribers;
    }

    @Override
    public void subscribe(EventType eventType, EventListener listener) {
        this.subscribers.get(eventType).add(listener);
    }

    @Override
    public void unsubscribe(EventType eventType, EventListener listener) {
        this.subscribers.get(eventType).remove(listener);
    }

    @Override
    public void notify(EventType eventType) {
        List<EventListener> listeners = this.subscribers.get(eventType);
        for (EventListener listener : listeners)
            listener.update(eventType);
    }
}
