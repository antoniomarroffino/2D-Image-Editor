package ch.supsi.imageEditor.frontend.model.handleViewService;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public interface HandleViewModelInterface {
    void subscribe(EventType eventType, DataView view);
    void unsubscribe(EventType eventType, DataView view);
    List<DataView> getViewsOnEventType(EventType eventType);
}
