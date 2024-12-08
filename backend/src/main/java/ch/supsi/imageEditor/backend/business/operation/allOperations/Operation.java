package ch.supsi.imageEditor.backend.business.operation.allOperations;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

public interface Operation {
    AbstractImage doOperation(AbstractImage image);
}
