package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PgmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ExportPbmStrategyTest {
    private ExportPbmStrategy exportPbmStrategy;

    @BeforeEach
    public void setUp() {
        this.exportPbmStrategy = new ExportPbmStrategy();
    }

    @Test
    public void upTest() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        AbstractImage returnedImage = this.exportPbmStrategy.up(mockImage);
        Assertions.assertNull(returnedImage);
    }

    @Test
    public void downTest() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        Pixel mockPixel = Mockito.mock(Pixel.class);
        when(mockPixel.getRed()).thenReturn(128);
        when(mockImage.getWidth()).thenReturn(10);
        when(mockImage.getHeight()).thenReturn(10);
        when(mockImage.getPixel(anyInt(), anyInt())).thenReturn(mockPixel);
        AbstractImage returnedImage = this.exportPbmStrategy.down(mockImage);
        Assertions.assertNotNull(returnedImage);
        Assertions.assertEquals(10, returnedImage.getWidth());
        Assertions.assertEquals(10, returnedImage.getHeight());
        verify(mockImage, times(3)).getHeight();
        verify(mockImage, times(3)).getWidth();
        verify(mockImage, times(100)).getPixel(anyInt(), anyInt());
    }

    @Test
    public void downTest2() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        Pixel mockPixel = Mockito.mock(Pixel.class);
        when(mockImage.getWidth()).thenReturn(10);
        when(mockImage.getHeight()).thenReturn(10);
        when(mockImage.getPixel(anyInt(), anyInt())).thenReturn(mockPixel);
        when(mockPixel.getRed()).thenReturn(0);
        AbstractImage returnedImage = this.exportPbmStrategy.down(mockImage);
        Assertions.assertNotNull(returnedImage);
        Assertions.assertInstanceOf(PgmImage.class, returnedImage);
        PgmImage pgmImageResult = (PgmImage) returnedImage;
        Pixel resultPixel = pgmImageResult.getPixelMatrix()[0][0];
        Assertions.assertEquals(0, resultPixel.getRed());
        verify(mockImage, times(3)).getHeight();
        verify(mockImage, times(3)).getWidth();
        verify(mockImage, times(100)).getPixel(anyInt(), anyInt());
        verify(mockPixel, times(100)).getRed();
    }
}
