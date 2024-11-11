package ch.supsi.imageEditor.backend.business.images;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageFactoryTest {
    @BeforeEach
    public void beforeEach() {
        ImageFactory.instance = null;
    }

    @Test
    public void constructor() {
        ImageFactory imageFactory = new ImageFactory();
        Assertions.assertNotNull(imageFactory);
        Assertions.assertNotNull(imageFactory.getImageDataAccess());
        Assertions.assertNotNull(imageFactory.getImageReaders());
        Assertions.assertNotNull(imageFactory.getImageReaderProperties());
        Assertions.assertNotNull(imageFactory.getRecentFilesList());

        Assertions.assertNull(imageFactory.getCurrentImage());
        Assertions.assertNull(imageFactory.getCurrentImageReader());
    }

    @Test
    public void instance() {
        ImageFactory imageFactory = ImageFactory.getInstance();
        Assertions.assertNotNull(imageFactory);
        Assertions.assertNotNull(ImageFactory.instance);
        Assertions.assertNotNull(imageFactory.getImageDataAccess());
        Assertions.assertNotNull(imageFactory.getImageReaders());
        Assertions.assertNotNull(imageFactory.getImageReaderProperties());
        Assertions.assertNotNull(imageFactory.getRecentFilesList());

        Assertions.assertNull(imageFactory.getCurrentImage());
        Assertions.assertNull(imageFactory.getCurrentImageReader());
    }

    @Test
    public void checkSingleton() {
        ImageFactory imageFactory1 = ImageFactory.getInstance();
        ImageFactory imageFactory2 = ImageFactory.getInstance();
        Assertions.assertEquals(imageFactory1, imageFactory2);
    }
}