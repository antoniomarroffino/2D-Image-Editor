package ch.supsi.imageEditor.frontend.controller;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppControllerTest {
    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {
        });
    }


    @BeforeEach
    public void beforeEach() {
        AppController.instance = null;
    }

    @Test
    public void constructor() {
        AppController appController = new AppController();
        Assertions.assertNotNull(appController);
        Assertions.assertNotNull(appController.getAppModel());
        Assertions.assertNotNull(appController.getAboutView());
        Assertions.assertNotNull(appController.getLanguageModel());
        Assertions.assertNotNull(appController.getHelpGuideView());
    }

    @Test
    public void instance() {
        AppController appController = AppController.getInstance();
        Assertions.assertNotNull(appController);
        Assertions.assertNotNull(AppController.instance);
        Assertions.assertNotNull(appController.getAppModel());
        Assertions.assertNotNull(appController.getAboutView());
        Assertions.assertNotNull(appController.getLanguageModel());
        Assertions.assertNotNull(appController.getHelpGuideView());
    }

    @Test
    public void checkSingleton() {
        AppController appController1 = AppController.getInstance();
        AppController appController2 = AppController.getInstance();
        Assertions.assertEquals(appController1, appController2);
    }
}