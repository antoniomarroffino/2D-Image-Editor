package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.application.image.ImageControllerInterface;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.frontend.model.AbstractModel;

public class ImageModel extends AbstractModel implements ImageModelInterface {
    protected static ImageModel instance = null;
    private final ImageControllerInterface imageController;
    private AbstractImage currentImage;

    protected ImageModel() {
        this.imageController = ImageController.getInstance();
        this.currentImage = null;
    }

    public static ImageModel getInstance() {
        return instance == null ? instance = new ImageModel() : instance;
    }

    @Override
    public void loadCurrentImage() {
        this.currentImage = this.imageController.getImage();
    }

    @Override
    public void closeCurrentImage() {
        this.currentImage = null;
    }

    @Override
    public AbstractImage getImage() {
        return this.currentImage;
    }

    @Override
    public int getWidthImage() {
        if (this.currentImage != null)
            return this.currentImage.getWidth();
        return 0;
    }

    @Override
    public int getHeightImage() {
        if (this.currentImage != null)
            return this.currentImage.getHeight();
        return 0;
    }

    @Override
    public int getPixelRed(int x, int y) {
        return this.currentImage.getPixel(x, y).getRed();
    }

    @Override
    public int getPixelGreen(int x, int y) {
        return this.currentImage.getPixel(x, y).getGreen();
    }

    @Override
    public int getPixelBlue(int x, int y) {
        return this.currentImage.getPixel(x, y).getBlue();
    }
}
