package ch.supsi.imageEditor.frontend.model.about;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AboutModelTest {
    @BeforeEach
    public void beforeEach() {
        AboutModel.instance = null;
    }

    @Test
    public void constructor() {
        AboutModel aboutModel = new AboutModel();
        Assertions.assertNotNull(aboutModel);
        Assertions.assertNotNull(aboutModel.getBuildProperties());
    }

    @Test
    public void instance() {
        AboutModel aboutModel = AboutModel.getInstance();
        Assertions.assertNotNull(aboutModel);
        Assertions.assertNotNull(AboutModel.instance);
        Assertions.assertNotNull(aboutModel.getBuildProperties());
    }

    @Test
    public void checkSingleton() {
        AboutModel aboutModel1 = AboutModel.getInstance();
        AboutModel aboutModel2 = AboutModel.getInstance();
        Assertions.assertEquals(aboutModel1, aboutModel2);
    }
}