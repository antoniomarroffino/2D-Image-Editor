package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PgmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ExportPpmStrategyTest {
    private ExportPpmStrategy exportPpmStrategy;

    @BeforeEach
    public void setUp() {
        this.exportPpmStrategy = new ExportPpmStrategy();
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
        when(mockPixel.getGreen()).thenReturn(128);
        when(mockPixel.getBlue()).thenReturn(128);
        ExportPpmStrategy exportPpmStrategy = new ExportPpmStrategy();
        AbstractImage returnedImage = exportPpmStrategy.up(mockImage);
        Assertions.assertNotNull(returnedImage);
        Assertions.assertInstanceOf(PgmImage.class, returnedImage);
        Assertions.assertEquals(10, returnedImage.getWidth());
        Assertions.assertEquals(10, returnedImage.getHeight());
        verify(mockImage, times(3)).getHeight();
        verify(mockImage, times(3)).getWidth();
        verify(mockImage, times(300)).getPixel(anyInt(), anyInt());
    }

    @Test
    public void downTest() {
        AbstractImage mockImage = Mockito.mock(AbstractImage.class);
        AbstractImage returnedImage = this.exportPpmStrategy.down(mockImage);
        Assertions.assertNull(returnedImage);
    }
}

