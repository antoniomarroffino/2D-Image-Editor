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

        for (int i = 0; i < image.getHeight(); i++)
            for (int j = 0; j < image.getWidth(); j++)
                pgmImage.getPixelMatrix()[i][j] = new Pixel(image.getPixel(i, j).getRed() >= 1 ? 255 : 0);
        return pgmImage;
    }
}
