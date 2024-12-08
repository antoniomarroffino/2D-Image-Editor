package ch.supsi.imageEditor.frontend;

import org.junit.jupiter.api.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class MainFxHelpTest extends AbstractMainGUITest {

    @Test
    public void walkThrough() {
        testViewWhenAbout();
        testViewWhenHelp();
    }

    private void testViewWhenAbout() {
        step("View when about", () -> {
            clickOn("#helpMenu");
            clickOn("#aboutMenuItem");
            verifyThat("#aboutAlert", isVisible());
            clickOn("#info-ok-button");
        });
    }

    private void testViewWhenHelp() {
        step("View when help", () -> {
            clickOn("#helpMenu");
            clickOn("#helpMenuItem");
            verifyThat("#helpGuideAnchorPane", isVisible());
            clickOn("#buttonClose");
        });
    }
}
