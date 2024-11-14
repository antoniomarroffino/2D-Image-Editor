package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.image;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class ImageViewFXMLTest {
    private ResourceBundle resourceBundle;

    @BeforeAll
    public static void beforeClass() {
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @BeforeEach
    public void beforeEach() {
        ImageViewFXML.instance = null;
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels", Locale.forLanguageTag("it-CH"));
    }

    @Test
    public void constructor() {
        ImageViewFXML imageViewFXML = new ImageViewFXML();
        Assertions.assertNotNull(imageViewFXML);
    }

    @Test
    public void instance() {
        ImageViewFXML imageViewFXML = ImageViewFXML.getInstance(this.resourceBundle);
        Assertions.assertNotNull(imageViewFXML);
        Assertions.assertNotNull(ImageViewFXML.instance);
    }

    @Test
    public void checkSingleton() {
        ImageViewFXML imageViewFXML1 = ImageViewFXML.getInstance(this.resourceBundle);
        ImageViewFXML imageViewFXML2 = ImageViewFXML.getInstance(this.resourceBundle);
        Assertions.assertEquals(imageViewFXML1, imageViewFXML2);
    }
}