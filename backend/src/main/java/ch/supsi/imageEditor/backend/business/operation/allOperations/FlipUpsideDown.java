package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

public class FlipUpsideDown implements Operation {
    @Override
    public AbstractImage doOperation(AbstractImage image) {
        System.out.println(getClass().getSimpleName());
        return image;
    }
}
