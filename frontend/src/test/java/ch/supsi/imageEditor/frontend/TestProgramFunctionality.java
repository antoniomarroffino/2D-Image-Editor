package ch.supsi.imageEditor.frontend;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.text.SimpleDateFormat;

import static org.testfx.api.FxAssert.verifyThat;

public class TestProgramFunctionality extends AbstractMainGUITest {
    private final StringBuilder infoBarMessages = new StringBuilder();

    @Test
    public void walkThrough() {
//        changeLanguage();
//        openImage();
//        imageInfo();
//        pipeline();
//        savingImage();
//        closingImage();
//        closingImageConfirm();
        export();
    }

    private void changeLanguage() {
        step("Change Language", () -> {
            clickOn("#editMenu");
            clickOn("#languageMenu");
            clickOn("#italian");
            clickOn("#editMenu");
            clickOn("#languageMenu");
            clickOn("#english");
            clickOn("#editMenu");
            clickOn("#languageMenu");
            clickOn("#english");
            infoBarMessages.insert(0, "Language is updated correctly. You have to restart app\n\n" +
                    "Language is updated correctly. You have to restart app\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
        });
    }

    private void openImage() {
        step("Open Image", () -> {
            clickOn("#fileMenu");
            clickOn("#openMenuItem");
            infoBarMessages.insert(0, "Image is loaded correctly\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
        });
    }

    private void imageInfo() {
        step("Image Info", () -> {
            Label label = lookup("#nameLabel").query();
            Assertions.assertEquals("Name: P3", label.getText());
            label = lookup("#formatLabel").query();
            Assertions.assertEquals("Format: ppm", label.getText());
            label = lookup("#dimensionLabel").query();
            Assertions.assertEquals("Dimension: 103 bytes", label.getText());
            label = lookup("#modificationDateLabel").query();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Assertions.assertEquals("Modification date: " + sdf.format(file.lastModified()), label.getText());
        });
    }

    private void pipeline() {
        addOperation();
        clearPipeline();
        runPipeline();
    }

    private void addOperation() {
        step("Add Operation to Pipeline", () -> {
            clickOn("#negative");
            infoBarMessages.insert(0, "Operation is added to pipeline correctly\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
            VBox vbox = lookup("#pipelineVBox").query();
            Assertions.assertEquals(1, vbox.getChildren().size());
        });
    }

    private void clearPipeline() {
        step("Clear Pipeline", () -> {
            clickOn("#deleteButton");
            infoBarMessages.insert(0, "Pipeline is now clear\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
            VBox vbox = lookup("#pipelineVBox").query();
            Assertions.assertEquals(0, vbox.getChildren().size());
        });
    }

    private void runPipeline() {
        step("Run Pipeline", () -> {
            clickOn("#negative");
            infoBarMessages.insert(0, "Operation is added to pipeline correctly\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));

            clickOn("#playButton");
            infoBarMessages.insert(0, "Pipeline is executed\n\n");
            infoBarMessages.insert(0, "Pipeline is now clear\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
            VBox vbox = lookup("#pipelineVBox").query();
            Assertions.assertEquals(0, vbox.getChildren().size());
        });
    }

    private void savingImage() {
        step("Saving Image", () -> {
            clickOn("#fileMenu");
            clickOn("#saveMenuItem");
            infoBarMessages.insert(0, "Image saved correctly\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
            Label label = lookup("#modificationDateLabel").query();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Assertions.assertEquals("Modification date: " + sdf.format(file.lastModified()), label.getText());
        });
    }

    private void closingImage() {
        step("Closing Image", () -> {
            clickOn("#fileMenu");
            clickOn("#closeMenuItem");
            infoBarMessages.insert(0, "Image is closed correctly\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
        });
    }

    private void closingImageConfirm() {
        step("Closing Image Confirmation", () -> {
            clickOn("#fileMenu");
            clickOn("#openMenuItem");
            clickOn("#negative");
            clickOn("#playButton");
            clickOn("#fileMenu");
            clickOn("#closeMenuItem");
            //VBox vbox = lookup("#confirmationVBox").query();
            //Assertions.assertTrue(vbox.isVisible());
            //clickOn("#buttonNo");
            sleep(5000);
        });
    }

    private void export() {
        step("Export", () -> {
            clickOn("#fileMenu");
            clickOn("#openMenuItem");
            clickOn("#fileMenu");
            clickOn("#saveAsMenuItem");
            infoBarMessages.insert(0, "Image is loaded correctly\n\n");
            infoBarMessages.insert(0, "Image saved correctly\n\n");
            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(infoBarMessages.toString()));
        });
    }
}
