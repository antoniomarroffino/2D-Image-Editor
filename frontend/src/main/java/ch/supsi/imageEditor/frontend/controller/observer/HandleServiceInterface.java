package ch.supsi.imageEditor.frontend.controller.observer;

import ch.supsi.imageEditor.frontend.adapter.Component;

import java.util.function.Consumer;

public interface HandleServiceInterface {
    void subscribe(EventOnApplication eventType, Consumer<Component> consumer);

    void unsubscribe(EventOnApplication eventType, Consumer<Component> consumer);

    void notify(EventOnApplication eventType, Component event);
}
