package ch.supsi.imageEditor.backend;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReaderTest {
    private ImageFactory factory;

    @BeforeEach
    public void setUp() {
        this.factory = ImageFactory.getInstance();
    }

    @Test
    public void testFormatNotExists() {
        assertThrows(FormatNotSupportedException.class, () -> this.factory.readImage("test.png"));
    }

    @Test
    public void testFileNotExists() {
        assertThrows(IOException.class, () -> this.factory.readImage("test.ppm"));
    }

    @Test
    public void testPBMReadImage() {
        try{
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P1.pbm");
            AbstractImage image = this.factory.getImage();
            assertEquals(4, image.getWidth());
            assertEquals(4, image.getHeight());
        }catch(IOException | FormatNotSupportedException | ImageHeaderUncorrectException ignored){
            ;
        }
    }

    @Test
    public void testPGMReadImage() {
        try{
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P2.pgm");
            AbstractImage image = this.factory.getImage();
            assertEquals(5, image.getWidth());
            assertEquals(5, image.getHeight());
        }catch(IOException | FormatNotSupportedException | ImageHeaderUncorrectException ignored){
            ;
        }
    }

    @Test
    public void testPPMReadImage() {
        try{
            this.factory.readImage("./src/test/java/ch/supsi/imageEditor/backend/images/P3.ppm");
            AbstractImage image = this.factory.getImage();
            assertEquals(3, image.getWidth());
            assertEquals(2, image.getHeight());
        }catch(IOException | FormatNotSupportedException | ImageHeaderUncorrectException ignored){
            ;
        }
    }

}
