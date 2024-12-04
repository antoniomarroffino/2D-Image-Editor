package ch.supsi.imageEditor.backend.business.images.export;

import ch.supsi.imageEditor.backend.business.images.AbstractImage;

public interface ExportStrategy {
    AbstractImage up(AbstractImage image);

    AbstractImage down(AbstractImage image);
}
