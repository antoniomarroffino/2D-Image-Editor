package ch.supsi.imageEditor.backend.application.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageControllerTest {
    @BeforeEach
    public void beforeEach() {
        ImageController.instance = null;
    }

    @Test
    public void constructor() {
        ImageController imageController = new ImageController();
        Assertions.assertNotNull(imageController);
    }

    @Test
    public void instance() {
        ImageController imageController = ImageController.getInstance();
        Assertions.assertNotNull(imageController);
        Assertions.assertNotNull(ImageController.instance);
    }

    @Test
    public void checkSingleton() {
        ImageController imageController1 = ImageController.getInstance();
        ImageController imageController2 = ImageController.getInstance();
        Assertions.assertEquals(imageController1, imageController2);
    }
}