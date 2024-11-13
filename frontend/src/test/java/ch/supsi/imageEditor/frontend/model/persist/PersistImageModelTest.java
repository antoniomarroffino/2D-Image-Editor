package ch.supsi.imageEditor.frontend.model.persist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersistImageModelTest {
    @BeforeEach
    public void beforeEach() {
        PersistImageModel.instance = null;
    }

    @Test
    public void constructor() {
        PersistImageModel persistImageModel = new PersistImageModel();
        Assertions.assertNotNull(persistImageModel);
        Assertions.assertNotNull(persistImageModel.getImageController());
        Assertions.assertNull(persistImageModel.getCurrentFile());
        Assertions.assertFalse(persistImageModel.isAlreadySave());
    }

    @Test
    public void instance() {
        PersistImageModel persistImageModel = PersistImageModel.getInstance();
        Assertions.assertNotNull(persistImageModel);
        Assertions.assertNotNull(PersistImageModel.instance);
        Assertions.assertNotNull(persistImageModel.getImageController());
        Assertions.assertNull(persistImageModel.getCurrentFile());
        Assertions.assertFalse(persistImageModel.isAlreadySave());
    }

    @Test
    public void checkSingleton() {
        PersistImageModel persistImageModel1 = PersistImageModel.getInstance();
        PersistImageModel persistImageModel2 = PersistImageModel.getInstance();
        Assertions.assertEquals(persistImageModel1, persistImageModel2);
    }
}