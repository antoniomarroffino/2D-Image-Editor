package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.backend.business.images.PNM.Pixel;
import ch.supsi.imageEditor.frontend.exception.ImageTooBigException;

public interface ImageModelInterface {
    void setMaxWidthImage(double maxWidthImage);
    void setMaxHeightImage(double maxHeightImage);
    void loadCurrentImage() throws ImageTooBigException;
    AbstractImage getImage();
    int getWidthImage();
    int getHeightImage();
    Pixel getPixel(int x, int y);
}
