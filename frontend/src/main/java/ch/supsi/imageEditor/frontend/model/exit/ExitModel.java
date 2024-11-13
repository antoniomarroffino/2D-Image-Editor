package ch.supsi.imageEditor.frontend.model.exit;

import javafx.stage.Stage;

public class ExitModel implements ExitModelInterface {
    protected static ExitModel instance;
    private Stage stage;

    protected ExitModel() {
        this.stage = null;
    }

    public static ExitModel getInstance() {
        return instance == null ? instance = new ExitModel() : instance;
    }

    Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Runnable closeApplication() {
        return () -> {
            if (stage != null)
                stage.close();
        };
    }
}
