package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

public interface ImageModelInterface {
    void loadCurrentImage();

    AbstractImage getImage();

    int getWidthImage();

    int getHeightImage();

    int getPixelRed(int x, int y);

    int getPixelGreen(int x, int y);

    int getPixelBlue(int x, int y);
}
