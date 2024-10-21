package ch.supsi.imageEditor.backend.application.image;

import java.util.Set;

public interface ImageControllerInterface {
    void displayImage(String path);

    Set<String> getSupportedFormat();
}
