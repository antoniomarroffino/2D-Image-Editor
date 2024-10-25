package ch.supsi.imageEditor.frontend.controller.persisting;

import ch.supsi.imageEditor.frontend.adapter.Component;

public interface PersistImageControllerInterface {
    void openImage(Component component);
    void saveImage(Component component);
    void saveImageAs(Component component);
}
