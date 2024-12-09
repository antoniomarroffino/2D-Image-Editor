package ch.supsi.imageEditor.frontend;

import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractMainGUITest extends ApplicationTest {
    protected static final Logger LOGGER = Logger.getAnonymousLogger();
    protected int stepNo;
    protected URL fileResource = this.getClass().getClassLoader().getResource("images/P3.ppm");
    protected File file;
    @TempDir
    protected Path tempDir;
    protected File fileExported;
    protected SavingViewFXML mockedSavingViewFXML = mock(SavingViewFXML.class);

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
        setupFile();
        try (MockedStatic<SavingViewFXML> mockedStaticSavingViewFxml = Mockito.mockStatic(SavingViewFXML.class)) {
            when(mockedSavingViewFXML.getOpenFile(anySet())).thenReturn(file);
            when(mockedSavingViewFXML.getSaveFile(anySet())).thenReturn(fileExported);
            mockedStaticSavingViewFxml.when(SavingViewFXML::getInstance).thenReturn(mockedSavingViewFXML);

            final MainFX main = new MainFX();
            stage.toFront();
            main.start(stage);
        }
    }

    private void setupFile() {
        file = tempDir.resolve("P3.ppm").toFile();
        try (InputStream in = fileResource.openStream()) {
            Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) { }
        fileExported = tempDir.resolve("exported_image.pbm").toFile();
    }
}
