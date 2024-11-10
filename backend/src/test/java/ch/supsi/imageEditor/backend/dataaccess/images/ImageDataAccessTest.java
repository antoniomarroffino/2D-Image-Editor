package ch.supsi.imageEditor.backend.dataaccess.images;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageDataAccessTest {
    @BeforeEach
    public void beforeEach() {
        ImageDataAccess.instance = null;
    }

    @Test
    public void constructor() {
        ImageDataAccess imageDataAccess = new ImageDataAccess();
        Assertions.assertNotNull(imageDataAccess);
    }

    @Test
    public void instance() {
        ImageDataAccess imageDataAccess = ImageDataAccess.getInstance();
        Assertions.assertNotNull(imageDataAccess);
        Assertions.assertNotNull(ImageDataAccess.instance);
    }

    @Test
    public void checkSingleton() {
        ImageDataAccess imageDataAccess1 = ImageDataAccess.getInstance();
        ImageDataAccess imageDataAccess2 = ImageDataAccess.getInstance();
        Assertions.assertEquals(imageDataAccess1, imageDataAccess2);
    }

}