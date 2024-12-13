package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PgmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class ExportPpmStrategy implements ExportStrategy {
    @Override
    public AbstractImage up(AbstractImage image) {
        PgmImage pgmImage = new PgmImage();
        pgmImage.setWidth(image.getWidth());
        pgmImage.setHeight(image.getHeight());
        pgmImage.setPixel(new Pixel[image.getHeight()][image.getWidth()]);
        pgmImage.setMaxIntensity(image.getMaxIntensity());
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                int gray = (int) (image.getPixel(i, j).getRed() * 0.299 + image.getPixel(i, j).getGreen() * 0.587 + image.getPixel(i, j).getBlue() * 0.114);
                pgmImage.getPixelMatrix()[i][j] = new Pixel(gray);
            }
        return pgmImage;
    }

    @Override
    public AbstractImage down(AbstractImage image) {
        return null;
    }
}
