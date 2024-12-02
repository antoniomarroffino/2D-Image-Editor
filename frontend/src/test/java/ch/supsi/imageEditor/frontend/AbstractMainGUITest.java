package ch.supsi.imageEditor.frontend;

import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractMainGUITest extends ApplicationTest {
    protected static final Logger LOGGER = Logger.getAnonymousLogger();
    protected int stepNo;

    @BeforeAll
    public static void setupSpec() {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }

    protected void step(final String step, final Runnable runnable) {
        ++stepNo;
        LOGGER.info("STEP" + stepNo + ":" + step);
        runnable.run();
        LOGGER.info("STEP" + stepNo + ":" + "end");
    }

    public void start(final Stage stage) throws Exception {
        try (MockedStatic<SavingViewFXML> mockedStaticSavingViewFxml = Mockito.mockStatic(SavingViewFXML.class)) {
            SavingViewFXML mockedSavingViewFXML = mock(SavingViewFXML.class);
            when(mockedSavingViewFXML.getOpenFile(anySet())).thenReturn(new File("./src/test/java/ch/supsi/imageEditor/frontend/P3.ppm"));
            mockedStaticSavingViewFxml.when(SavingViewFXML::getInstance).thenReturn(mockedSavingViewFXML);

            final MainFX main = new MainFX();
            stage.toFront();
            main.start(stage);
        }
    }
}
