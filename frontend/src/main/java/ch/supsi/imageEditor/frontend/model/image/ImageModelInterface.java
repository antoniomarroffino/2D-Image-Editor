package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.frontend.exception.ImageTooBigException;

public interface ImageModelInterface {
    void setMaxWidthImage(double maxWidthImage);

    void setMaxHeightImage(double maxHeightImage);

    void loadCurrentImage() throws ImageTooBigException;

    void closeCurrentImage();

    AbstractImage getImage();

    int getWidthImage();

    int getHeightImage();

    int getPixelRed(int x, int y);

    int getPixelGreen(int x, int y);

    int getPixelBlue(int x, int y);
}
