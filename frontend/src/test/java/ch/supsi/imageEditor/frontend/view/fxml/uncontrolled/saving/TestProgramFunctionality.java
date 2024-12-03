package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import ch.supsi.imageEditor.frontend.AbstractMainGUITest;
import org.junit.jupiter.api.Test;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class TestProgramFunctionality extends AbstractMainGUITest {

    @Test
    public void walkThrough() {
        openImage();
    }

    private void openImage() {
        step("File Menu", () -> {
            clickOn("#fileMenu");
            clickOn("#openMenuItem");

            verifyThat("#infoBarTextArea", TextInputControlMatchers.hasText(
                    "Image is loaded correctly")
            );
        });
    }
}
