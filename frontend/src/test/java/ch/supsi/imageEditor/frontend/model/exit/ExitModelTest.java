package ch.supsi.imageEditor.frontend.model.exit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExitModelTest {
    @BeforeEach
    public void beforeEach() {
        ExitModel.instance = null;
    }

    @Test
    public void constructor() {
        ExitModel exitModel = new ExitModel();
        Assertions.assertNotNull(exitModel);
        Assertions.assertNull(exitModel.getStage());
    }

    @Test
    public void instance() {
        ExitModel exitModel = ExitModel.getInstance();
        Assertions.assertNotNull(exitModel);
        Assertions.assertNotNull(ExitModel.instance);
        Assertions.assertNull(exitModel.getStage());
    }

    @Test
    public void checkSingleton() {
        ExitModel exitModel1 = ExitModel.getInstance();
        ExitModel exitModel2 = ExitModel.getInstance();
        Assertions.assertEquals(exitModel1, exitModel2);
    }
}