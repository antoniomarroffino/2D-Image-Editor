package ch.supsi.imageEditor.frontend.adapter;

import javafx.scene.control.MenuItem;

public class MenuItemAdapter implements Component {
    private final MenuItem menuItem;

    public MenuItemAdapter(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String getId() {
        return menuItem.getId();
    }
}
