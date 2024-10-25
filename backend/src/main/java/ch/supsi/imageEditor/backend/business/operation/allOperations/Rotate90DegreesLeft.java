package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

public class Rotate90DegreesLeft implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        System.out.println(this.getClass().getSimpleName());
        return image;
    }
}
