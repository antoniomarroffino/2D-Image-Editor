package ch.supsi.imageEditor.frontend.adapter;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ButtonAdapterTest {
    private Button button;

    @BeforeEach
    public void beforeEach() {
        JFXPanel fxPanel = new JFXPanel();
        this.button = new Button();
        this.button.setId("button");
    }

    @Test
    public void constructor() {
        ButtonAdapter buttonAdapter = new ButtonAdapter(this.button);
        assertEquals(this.button.getId(), buttonAdapter.getId());
    }
}