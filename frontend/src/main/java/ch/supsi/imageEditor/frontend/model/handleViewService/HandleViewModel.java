package ch.supsi.imageEditor.frontend.model.handleViewService;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.*;

public class HandleViewModel implements HandleViewModelInterface {
    private static HandleViewModel instance = null;
    private final Map<EventType, List<DataView>> mapEventOnView;

    private HandleViewModel() {
        this.mapEventOnView = new HashMap<>();
        Arrays.stream(EventType.values()).forEach(event -> this.mapEventOnView.put(event, new ArrayList<>()));
    }

    public static HandleViewModel getInstance() {
        return instance == null ? instance = new HandleViewModel() : instance;
    }

    @Override
    public void subscribe(EventType eventType, DataView view) {
        this.mapEventOnView.get(eventType).add(view);
    }

    @Override
    public void unsubscribe(EventType eventType, DataView view) {
        this.mapEventOnView.get(eventType).remove(view);
    }

    @Override
    public List<DataView> getViewsOnEventType(EventType eventType) {
        return this.mapEventOnView.get(eventType);
    }
}
