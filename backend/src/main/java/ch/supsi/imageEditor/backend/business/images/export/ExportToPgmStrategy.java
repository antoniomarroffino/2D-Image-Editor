package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.PbmImage;
import ch.supsi.imageEditor.backend.business.images.Pixel;

public class ExportToPgmStrategy implements ExportStrategy {
    private final int threshold = 127;

    @Override
    public AbstractImage export(AbstractImage image) {
        PbmImage pbmImage = new PbmImage();
        pbmImage.setWidth(image.getWidth());
        pbmImage.setHeight(image.getHeight());
        pbmImage.setPixel(new Pixel[image.getHeight()][image.getWidth()]);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int intensity = image.getPixel(i, j).getRed();
                pbmImage.getPixelMatrix()[i][j] = new Pixel(intensity > threshold ? 255 : 0);
            }
        }

        return pbmImage;
    }
}
