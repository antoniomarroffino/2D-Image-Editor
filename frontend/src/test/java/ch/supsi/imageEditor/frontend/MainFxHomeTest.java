package ch.supsi.imageEditor.frontend;

import com.sun.javafx.scene.control.ContextMenuContent;
import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class MainFxHomeTest extends AbstractMainGUITest {

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
            MenuItem openMenuItem = lookup("#openMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(openMenuItem.isVisible());
            Assertions.assertFalse(openMenuItem.isDisable());

            verifyThat("#openRecentMenu", isVisible());
            verifyThat("#openRecentMenu", isEnabled());

            MenuItem saveMenuItem = lookup("#saveMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(saveMenuItem.isVisible());
            Assertions.assertTrue(saveMenuItem.isDisable());

            MenuItem saveAsMenuItem = lookup("#saveAsMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(saveAsMenuItem.isVisible());
            Assertions.assertTrue(saveAsMenuItem.isDisable());

            MenuItem closeMenuItem = lookup("#closeMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(closeMenuItem.isVisible());
            Assertions.assertTrue(closeMenuItem.isDisable());

            MenuItem quitMenuItem = lookup("#quitMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(quitMenuItem.isVisible());
            Assertions.assertFalse(quitMenuItem.isDisable());
            clickOn("#fileMenu");
        });
    }

    private void testEditMenu() {
        step("Edit Menu", () -> {
            clickOn("#editMenu");
            verifyThat("#languageMenu", isVisible());
            verifyThat("#languageMenu", isEnabled());
            clickOn("#editMenu");
        });
    }

    private void testHelpMenu() {
        step("Help Menu", () -> {
            clickOn("#helpMenu");
            MenuItem aboutMenuItem = lookup("#aboutMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(aboutMenuItem.isVisible());
            Assertions.assertFalse(aboutMenuItem.isDisable());

            MenuItem helpMenuItem = lookup("#helpMenuItem").queryAs(ContextMenuContent.MenuItemContainer.class).getItem();
            Assertions.assertTrue(helpMenuItem.isVisible());
            Assertions.assertFalse(helpMenuItem.isDisable());
            clickOn("#helpMenu");
        });
    }

}
