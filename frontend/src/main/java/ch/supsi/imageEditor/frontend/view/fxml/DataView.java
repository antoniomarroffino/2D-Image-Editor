package ch.supsi.imageEditor.frontend.view.fxml;

import ch.supsi.imageEditor.backend.application.observer.EventType;

public interface DataView {
    void update(EventType eventType);
}
