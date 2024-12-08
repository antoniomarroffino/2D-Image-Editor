package ch.supsi.imageEditor.frontend.model.exit;

import javafx.stage.Stage;

public interface ExitModelInterface {
    void setStage(Stage stage);

    Runnable closeApplication();
}
