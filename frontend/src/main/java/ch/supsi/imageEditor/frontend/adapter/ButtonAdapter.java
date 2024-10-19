package ch.supsi.imageEditor.frontend.adapter;

import javafx.scene.control.Button;

public class ButtonAdapter implements Component{

    private final Button button;

    public ButtonAdapter(Button button) {
        this.button = button;
    }
    @Override
    public String getId() {
        return button.getId();
    }
}
