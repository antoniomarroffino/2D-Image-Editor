package ch.supsi.imageEditor.frontend;

import org.junit.jupiter.api.Test;

public class MainFxQuitTest extends AbstractMainGUITest {

    @Test
    public void walkThrough() {
        testExit();
    }

    private void testExit() {
        step("Quit", () -> {
            clickOn("#fileMenu");
            clickOn("#quitMenuItem");
        });
    }
}
