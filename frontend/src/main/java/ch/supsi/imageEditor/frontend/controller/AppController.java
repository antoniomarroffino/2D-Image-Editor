package ch.supsi.imageEditor.frontend.controller;

import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.AppModel;
import ch.supsi.imageEditor.frontend.model.language.LanguageModel;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.help.HelpGuideViewFXML;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.help.HelpGuideViewFXMLInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewInterface;
import ch.supsi.imageEditor.frontend.view.popup.about.AboutViewPopUp;

public class AppController implements AppControllerInterface {
    protected static AppController instance = null;
    private final AppModel appModel;
    private final LanguageModel languageModel;
    private final AboutViewInterface aboutView;
    private final HelpGuideViewFXMLInterface helpGuideView;

    protected AppController() {
        this.appModel = AppModel.getInstance();
        this.languageModel = LanguageModel.getInstance();
        this.aboutView = AboutViewPopUp.getInstance(this.languageModel.getCurrentResourceBundle());
        this.helpGuideView = HelpGuideViewFXML.getInstance(this.languageModel.getCurrentResourceBundle());
    }

    public static AppController getInstance() {
        return instance == null ? instance = new AppController() : instance;
    }

    AppModel getAppModel() {
        return this.appModel;
    }

    LanguageModel getLanguageModel() {
        return this.languageModel;
    }

    AboutViewInterface getAboutView() {
        return this.aboutView;
    }

    HelpGuideViewFXMLInterface getHelpGuideView() {
        return this.helpGuideView;
    }

    @Override
    public void about(Component node) {
        this.aboutView.showAboutInformation();
    }

    @Override
    public void help(Component node) {
        this.helpGuideView.showHelpInfo();
    }
}
