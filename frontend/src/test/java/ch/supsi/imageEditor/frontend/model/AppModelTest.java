package ch.supsi.imageEditor.frontend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppModelTest {
    @BeforeEach
    public void beforeEach() {
        AppModel.instance = null;
    }

    @Test
    public void constructor() {
        AppModel appModel = new AppModel();
        Assertions.assertNotNull(appModel);
    }

    @Test
    public void instance() {
        AppModel appModel = AppModel.getInstance();
        Assertions.assertNotNull(appModel);
        Assertions.assertNotNull(AppModel.instance);
    }

    @Test
    public void checkSingleton() {
        AppModel appModel1 = AppModel.getInstance();
        AppModel appModel2 = AppModel.getInstance();
        Assertions.assertEquals(appModel1, appModel2);
    }
}