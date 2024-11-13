package ch.supsi.imageEditor.frontend.view.fxml;

import javafx.application.Platform;

abstract public class InitializePlatform {
    private static boolean isInitialized = false;

    public static void initializedPlatform() {
        if (!isInitialized) {
            Platform.startup(() -> {
            });
            isInitialized = true;
        }
    }
}
