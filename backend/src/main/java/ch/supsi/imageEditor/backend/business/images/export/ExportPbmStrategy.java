package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PgmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class ExportPbmStrategy implements ExportStrategy {
    @Override
    public AbstractImage up(AbstractImage image) {
        return null;
    }

    @Override
    public AbstractImage down(AbstractImage image) {
        PgmImage pgmImage = new PgmImage();
        pgmImage.setWidth(image.getWidth());
        pgmImage.setHeight(image.getHeight());
        pgmImage.setPixel(new Pixel[image.getHeight()][image.getWidth()]);
        pgmImage.setMaxIntensity(255);
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                pgmImage.getPixelMatrix()[i][j] = new Pixel(image.getPixel(i, j).getRed() >= 1 ? 255 : 0);
        return pgmImage;
    }
}
