package ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingViewFXMLTest {
    @BeforeEach
    public void beforeEach() {
        SavingViewFXML.instance = null;
    }

    @Test
    public void constructor() {
        SavingViewFXML imageController = new SavingViewFXML();
        Assertions.assertNotNull(imageController);
    }

    @Test
    public void instance() {
        SavingViewFXML savingViewFXML = SavingViewFXML.getInstance();
        Assertions.assertNotNull(savingViewFXML);
        Assertions.assertNotNull(SavingViewFXML.instance);
    }

    @Test
    public void checkSingleton() {
        SavingViewFXML savingViewFXML1 = SavingViewFXML.getInstance();
        SavingViewFXML savingViewFXML2 = SavingViewFXML.getInstance();
        Assertions.assertEquals(savingViewFXML1, savingViewFXML2);
    }
}