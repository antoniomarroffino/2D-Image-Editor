package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.application.image.ImageControllerInterface;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.frontend.exception.ImageTooBigException;
import ch.supsi.imageEditor.frontend.model.AbstractModel;

public class ImageModel extends AbstractModel implements ImageModelInterface {
    private static ImageModelInterface instance;
    private static double MAX_WIDTH_IMAGE;
    private static double MAX_HEIGHT_IMAGE;

    static {
        MAX_HEIGHT_IMAGE = 0;
        MAX_WIDTH_IMAGE = 0;
    }

    private final ImageControllerInterface imageController;
    private AbstractImage currentImage;

    private ImageModel() {
        this.imageController = ImageController.getInstance();
        this.currentImage = null;
    }

    public static ImageModelInterface getInstance() {
        return instance == null ? instance = new ImageModel() : instance;
    }

    @Override
    public void setMaxWidthImage(double maxWidthImage) {
        MAX_WIDTH_IMAGE = maxWidthImage;
    }

    @Override
    public void setMaxHeightImage(double maxHeightImage) {
        MAX_HEIGHT_IMAGE = maxHeightImage;
    }

    @Override
    public void loadCurrentImage() throws ImageTooBigException {
        AbstractImage image = this.imageController.getImage();
        if (image.getWidth() > MAX_WIDTH_IMAGE || image.getHeight() > MAX_HEIGHT_IMAGE || image.getHeight() > MAX_WIDTH_IMAGE || image.getWidth() > MAX_HEIGHT_IMAGE)
            throw new ImageTooBigException("Image loaded is too big for current GUI. Image must be at most: "
                    + (int) MAX_WIDTH_IMAGE + " x " + (int) MAX_HEIGHT_IMAGE);
        this.currentImage = image;
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
