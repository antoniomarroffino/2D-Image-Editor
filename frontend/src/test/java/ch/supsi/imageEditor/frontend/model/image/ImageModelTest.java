package ch.supsi.imageEditor.frontend.model.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageModelTest {
    @BeforeEach
    public void beforeEach() {
        ImageModel.instance = null;
    }

    @Test
    public void constructor() {
        ImageModel imageModel = new ImageModel();
        Assertions.assertNotNull(imageModel);
    }

    @Test
    public void instance() {
        ImageModelInterface imageModel = ImageModel.getInstance();
        Assertions.assertNotNull(imageModel);
        Assertions.assertNotNull(ImageModel.instance);
    }

    @Test
    public void checkSingleton() {
        ImageModelInterface imageModel1 = ImageModel.getInstance();
        ImageModelInterface imageModel2 = ImageModel.getInstance();
        Assertions.assertEquals(imageModel1, imageModel2);
    }
}