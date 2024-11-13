package ch.supsi.imageEditor.frontend.controller.persisting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersistImageControllerTest {
    @BeforeEach
    public void beforeEach() {
        PersistImageController.instance = null;
    }

    @Test
    public void constructor() {
        PersistImageController persistImageController = new PersistImageController();
        Assertions.assertNotNull(persistImageController);
        Assertions.assertNotNull(persistImageController.getPersistImageModel());
        Assertions.assertNotNull(persistImageController.getPubSubModel());
        Assertions.assertNotNull(persistImageController.getExitModel());
        Assertions.assertNotNull(persistImageController.getErrorView());
        Assertions.assertNotNull(persistImageController.getSavingViewFXML());
    }

    @Test
    public void instance() {
        PersistImageController persistImageController = PersistImageController.getInstance();
        Assertions.assertNotNull(persistImageController);
        Assertions.assertNotNull(PersistImageController.instance);
        Assertions.assertNotNull(persistImageController.getPersistImageModel());
        Assertions.assertNotNull(persistImageController.getPubSubModel());
        Assertions.assertNotNull(persistImageController.getExitModel());
        Assertions.assertNotNull(persistImageController.getErrorView());
        Assertions.assertNotNull(persistImageController.getSavingViewFXML());

    }

    @Test
    public void checkSingleton() {
        PersistImageController persistImageController1 = PersistImageController.getInstance();
        PersistImageController persistImageController2 = PersistImageController.getInstance();
        Assertions.assertEquals(persistImageController1, persistImageController2);
    }
}