package ch.supsi.imageEditor.frontend.adapter;

import javafx.scene.control.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuItemAdapterTest {
    private MenuItem menuItem;

    @BeforeEach
    public void beforeEach() {
        this.menuItem = new MenuItem();
        this.menuItem.setId("menuItem");
    }

    @Test
    public void constructor() {
        MenuItemAdapter menuItemAdapter = new MenuItemAdapter(this.menuItem);
        assertEquals(this.menuItem.getId(), menuItemAdapter.getId());
    }
}