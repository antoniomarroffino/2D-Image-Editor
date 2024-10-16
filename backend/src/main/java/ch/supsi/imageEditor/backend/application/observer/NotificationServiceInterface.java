package ch.supsi.imageEditor.backend.application.observer;

public interface NotificationServiceInterface {
    void subscribe(EventType eventType, EventListener listener);
    void unsubscribe(EventType eventType, EventListener listener);
    void notify(EventType eventType);
}
