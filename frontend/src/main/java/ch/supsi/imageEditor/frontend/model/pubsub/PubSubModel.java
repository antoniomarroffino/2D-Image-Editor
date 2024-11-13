package ch.supsi.imageEditor.frontend.model.pubsub;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.backend.application.observer.NotificationService;
import ch.supsi.imageEditor.backend.application.observer.NotificationServiceInterface;

public class PubSubModel implements PubSubModelInterface {
    protected static PubSubModel instance = null;
    private final NotificationServiceInterface notificationService;

    protected PubSubModel() {
        this.notificationService = NotificationService.getInstance();
    }

    public static PubSubModel getInstance() {
        return instance == null ? instance = new PubSubModel() : instance;
    }

    NotificationServiceInterface getNotificationService() {
        return this.notificationService;
    }

    @Override
    public void subscribe(EventType eventType, EventListener listener) {
        this.notificationService.subscribe(eventType, listener);
    }

    @Override
    public void unsubscribe(EventType eventType, EventListener listener) {
        this.notificationService.unsubscribe(eventType, listener);
    }
}
