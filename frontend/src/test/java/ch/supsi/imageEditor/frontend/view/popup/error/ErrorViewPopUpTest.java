package ch.supsi.imageEditor.frontend.view.popup.error;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorViewPopUpTest {
    @BeforeEach
    public void beforeEach() {
        ErrorViewPopUp.instance = null;
    }

    @Test
    public void constructor() {
        ErrorViewPopUp errorViewPopUp = new ErrorViewPopUp();
        Assertions.assertNotNull(errorViewPopUp);
    }

    @Test
    public void instance() {
        ErrorViewPopUp errorViewPopUp = ErrorViewPopUp.getInstance();
        Assertions.assertNotNull(errorViewPopUp);
        Assertions.assertNotNull(ErrorViewPopUp.instance);
    }

    @Test
    public void checkSingleton() {
        ErrorViewPopUp errorViewPopUp1 = ErrorViewPopUp.getInstance();
        ErrorViewPopUp errorViewPopUp2 = ErrorViewPopUp.getInstance();
        Assertions.assertEquals(errorViewPopUp1, errorViewPopUp2);
    }
}