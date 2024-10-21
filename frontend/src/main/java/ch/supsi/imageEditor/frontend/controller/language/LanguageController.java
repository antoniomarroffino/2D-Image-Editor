package ch.supsi.imageEditor.frontend.controller.language;

import ch.supsi.imageEditor.backend.application.observer.EventListener;
import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModelInterface;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModel;
import ch.supsi.imageEditor.frontend.model.pubsub.PubSubModelInterface;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;

import java.util.List;

public class LanguageController implements LanguageControllerInterface, EventListener {
    private static LanguageController instance = null;
    private final LanguageModelInterface languageModel;
    private final PubSubModelInterface pubSubModel;
    private List<DataView> views;

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
        this.pubSubModel = PubSubModel.getInstance();
        this.pubSubModel.subscribe(EventType.CHANGE_LANGUAGE, this);
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void changeLanguage(Component node) {
        String languageKey = node.getId();
        this.languageModel.changeLanguage(languageKey);
    }

    @Override
    public void update(EventType eventType) {
        for (DataView view : this.views)
            view.update(eventType);
    }
}
