package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PbmImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PpmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class ExportPgmStrategy implements ExportStrategy {
    @Override
    public AbstractImage down(AbstractImage image) {
        PpmImage ppmImage = new PpmImage();
        ppmImage.setWidth(image.getWidth());
        ppmImage.setHeight(image.getHeight());
        ppmImage.setPixel(new Pixel[image.getHeight()][image.getWidth()]);
        ppmImage.setMaxIntensity(image.getMaxIntensity());
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                ppmImage.getPixelMatrix()[i][j] = new Pixel(image.getPixel(i, j).getRed());

        return ppmImage;
    }

    @Override
    public AbstractImage up(AbstractImage image) {
        int threshold = image.getMaxIntensity() / 2;
        PbmImage pbmImage = new PbmImage();
        pbmImage.setWidth(image.getWidth());
        pbmImage.setHeight(image.getHeight());
        pbmImage.setPixel(new Pixel[image.getHeight()][image.getWidth()]);
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                pbmImage.getPixelMatrix()[i][j] = new Pixel(image.getPixel(i, j).getRed() > threshold ? 255 : 0);

        return pbmImage;
    }
}
