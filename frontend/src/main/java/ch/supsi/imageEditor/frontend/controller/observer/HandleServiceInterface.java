package ch.supsi.imageEditor.frontend.controller.observer;

import javafx.scene.control.MenuItem;

import java.util.function.Consumer;

public interface HandleServiceInterface {
    void subscribe(EventOnApplication eventType, Consumer<MenuItem> consumer);
    void unsubscribe(EventOnApplication eventType, Consumer<MenuItem> consumer);
    void notify(EventOnApplication eventType, MenuItem event);
}
