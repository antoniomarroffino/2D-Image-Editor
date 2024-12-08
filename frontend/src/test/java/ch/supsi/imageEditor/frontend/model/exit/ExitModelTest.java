package ch.supsi.imageEditor.frontend.model.exit;

import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ExitModelTest {
    private ExitModel exitModel;

    @BeforeEach
    public void beforeEach() {
        ExitModel.instance = null;
    }

    @Test
    public void constructor() {
        this.exitModel = new ExitModel();
        Assertions.assertNotNull(this.exitModel);
        Assertions.assertNull(this.exitModel.getStage());
    }

    @Test
    public void instance() {
        this.exitModel = ExitModel.getInstance();
        Assertions.assertNotNull(this.exitModel);
        Assertions.assertNotNull(ExitModel.instance);
        Assertions.assertNull(this.exitModel.getStage());
    }

    @Test
    public void checkSingleton() {
        ExitModel exitModel1 = ExitModel.getInstance();
        ExitModel exitModel2 = ExitModel.getInstance();
        Assertions.assertEquals(exitModel1, exitModel2);
    }

    @Test
    void closeApplicationWithStageSet() {
        Stage mockStage = Mockito.mock(Stage.class);
        this.exitModel = ExitModel.getInstance();
        exitModel.setStage(mockStage);
        Runnable closeTask = exitModel.closeApplication();
        closeTask.run();
        verify(mockStage, times(1)).close();
    }

    @Test
    void closeApplicationWithoutStageSet() {
        this.exitModel = ExitModel.getInstance();
        exitModel.setStage(null);
        Runnable closeTask = exitModel.closeApplication();
        closeTask.run();
    }
}