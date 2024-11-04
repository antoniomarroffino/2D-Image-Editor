package ch.supsi.imageEditor.frontend;

import org.junit.jupiter.api.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;

public class MainFxTest extends AbstractMainGUITest {

    @Test
    public void walkThrough() {
        testMainScene();
        testFileMenu();
        testEditMenu();
        testHelpMenu();
    }

    private void testMainScene() {
        step("Main Scene", () -> {
            verifyThat("#fileMenu", isVisible());
            verifyThat("#editMenu", isVisible());
            verifyThat("#helpMenu", isVisible());
        });
    }

    private void testFileMenu() {
        step("File Menu", () -> {
            clickOn("#fileMenu");
            verifyThat("#openMenuItem", isVisible());
            verifyThat("#openMenuItem", isEnabled());
            verifyThat("#openRecentMenu", isVisible());
            verifyThat("#openRecentMenu", isEnabled());
            verifyThat("#saveMenuItem", isVisible());
            verifyThat("#saveMenuItem", isDisabled());
            verifyThat("#saveAsMenuItem", isVisible());
            verifyThat("#saveAsMenuItem", isDisabled());
            verifyThat("#exitMenuItem", isVisible());
            verifyThat("#exitMenuItem", isDisabled());
            verifyThat("#quitMenuItem", isVisible());
            verifyThat("#quitMenuItem", isEnabled());
        });
    }

    private void testEditMenu() {
        step("Edit Menu", () -> {
            clickOn("#editMenu");
            verifyThat("#languageMenu", isVisible());
            verifyThat("#languageMenu", isEnabled());
        });
    }

    private void testHelpMenu() {
        step("Help Menu", () -> {
            clickOn("#helpMenu");
            verifyThat("#aboutMenuItem", isVisible());
            verifyThat("#aboutMenuItem", isEnabled());
            verifyThat("#helpMenuItem", isVisible());
            verifyThat("#helpMenuItem", isEnabled());
        });
    }

}
