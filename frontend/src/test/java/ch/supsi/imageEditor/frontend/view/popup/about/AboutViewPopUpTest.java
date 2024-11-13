package ch.supsi.imageEditor.frontend.view.popup.about;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AboutViewPopUpTest {
    @BeforeEach
    public void beforeEach() {
        AboutViewPopUp.instance = null;
    }

    @Test
    public void constructor() {
        AboutViewPopUp aboutViewPopUp = new AboutViewPopUp();
        Assertions.assertNotNull(aboutViewPopUp);
    }
/*
    @Test
    public void instance() {
        AboutViewPopUp aboutViewPopUp = AboutViewPopUp.getInstance();
        Assertions.assertNotNull(aboutViewPopUp);
        Assertions.assertNotNull(AboutViewPopUp.instance);
    }

    @Test
    public void checkSingleton() {
        AboutViewPopUp aboutViewPopUp1 = AboutViewPopUp.getInstance();
        AboutViewPopUp aboutViewPopUp2 = AboutViewPopUp.getInstance();
        Assertions.assertEquals(aboutViewPopUp1, aboutViewPopUp2);
    }

 */
}