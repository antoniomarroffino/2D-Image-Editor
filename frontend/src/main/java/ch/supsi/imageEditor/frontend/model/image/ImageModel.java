package ch.supsi.imageEditor.frontend.model.image;

import ch.supsi.imageEditor.backend.application.image.ImageController;
import ch.supsi.imageEditor.backend.application.image.ImageControllerInterface;
import ch.supsi.imageEditor.backend.business.images.AbstractImage;
import ch.supsi.imageEditor.frontend.model.AbstractModel;

public class ImageModel extends AbstractModel implements ImageModelInterface {
    private static ImageModelInterface instance;

    private final ImageControllerInterface imageController;

    private ImageModel() {
        this.imageController = ImageController.getInstance();
    }

    public static ImageModelInterface getInstance() {
        return instance == null ? instance = new ImageModel() : instance;
    }

    @Override
    public AbstractImage getImage() {
        return this.imageController.getImage();
    }
}
