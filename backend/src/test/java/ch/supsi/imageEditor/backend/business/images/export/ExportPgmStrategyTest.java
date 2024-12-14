package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PbmImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PpmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ExportPgmStrategyTest {
    private ExportPgmStrategy exportPgmStrategy;

    @BeforeEach
    public void setUp() {
        this.exportPgmStrategy = new ExportPgmStrategy();
    }

    @Test
    public void downTest() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        Pixel mockPixel = Mockito.mock(Pixel.class);
        when(mockImage.getWidth()).thenReturn(10);
        when(mockImage.getHeight()).thenReturn(10);
        when(mockImage.getMaxIntensity()).thenReturn(255);
        when(mockImage.getPixel(anyInt(), anyInt())).thenReturn(mockPixel);
        when(mockPixel.getRed()).thenReturn(128);
        AbstractImage returnedImage = this.exportPgmStrategy.down(mockImage);
        Assertions.assertNotNull(returnedImage);
        Assertions.assertInstanceOf(PpmImage.class, returnedImage);
        Assertions.assertEquals(10, returnedImage.getWidth());
        Assertions.assertEquals(10, returnedImage.getHeight());
        PpmImage ppmImageResult = (PpmImage) returnedImage;
        Pixel resultPixel = ppmImageResult.getPixelMatrix()[0][0];
        Assertions.assertEquals(128, resultPixel.getRed());
        verify(mockImage, times(3)).getHeight();
        verify(mockImage, times(3)).getWidth();
        verify(mockImage, times(100)).getPixel(anyInt(), anyInt());
        verify(mockPixel, times(100)).getRed();
    }

    @Test
    public void upTest() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        Pixel mockPixel = Mockito.mock(Pixel.class);
        when(mockImage.getWidth()).thenReturn(10);
        when(mockImage.getHeight()).thenReturn(10);
        when(mockImage.getMaxIntensity()).thenReturn(255);
        when(mockImage.getPixel(anyInt(), anyInt())).thenReturn(mockPixel);
        when(mockPixel.getRed()).thenReturn(128);
        AbstractImage returnedImage = this.exportPgmStrategy.up(mockImage);
        Assertions.assertNotNull(returnedImage);
        Assertions.assertInstanceOf(PbmImage.class, returnedImage);
        Assertions.assertEquals(10, returnedImage.getWidth());
        Assertions.assertEquals(10, returnedImage.getHeight());
        verify(mockImage, times(3)).getHeight();
        verify(mockImage, times(3)).getWidth();
        verify(mockImage, times(100)).getPixel(anyInt(), anyInt());
        verify(mockPixel, times(100)).getRed();
    }

    @Test
    public void upTest2() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        Pixel mockPixel = Mockito.mock(Pixel.class);
        when(mockImage.getWidth()).thenReturn(10);
        when(mockImage.getHeight()).thenReturn(10);
        when(mockImage.getMaxIntensity()).thenReturn(100);
        when(mockImage.getPixel(anyInt(), anyInt())).thenReturn(mockPixel);
        when(mockPixel.getRed()).thenReturn(1);
        AbstractImage returnedImage = this.exportPgmStrategy.up(mockImage);
        Assertions.assertNotNull(returnedImage);
        Assertions.assertInstanceOf(PbmImage.class, returnedImage);
        Assertions.assertEquals(10, returnedImage.getWidth());
        Assertions.assertEquals(10, returnedImage.getHeight());
        verify(mockImage, times(3)).getHeight();
        verify(mockImage, times(3)).getWidth();
        verify(mockImage, times(100)).getPixel(anyInt(), anyInt());
        verify(mockPixel, times(100)).getRed();
    }
}

