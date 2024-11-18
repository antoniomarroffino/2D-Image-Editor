package ch.supsi.imageEditor.frontend.controller.persisting;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.exit.ExitModel;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModel;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import ch.supsi.imageEditor.frontend.view.fxml.uncontrolled.saving.SavingViewFXML;
import ch.supsi.imageEditor.frontend.view.popup.error.ErrorViewPopUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PersistImageControllerTest {
    private PersistImageController persistImageController;

    @BeforeEach
    public void beforeEach() {
        PersistImageController.instance = null;
    }

    @Test
    public void constructor() {
        persistImageController = new PersistImageController();
        Assertions.assertNotNull(persistImageController);
        Assertions.assertNotNull(persistImageController.getPersistImageModel());
        Assertions.assertNotNull(persistImageController.getPubSubModel());
        Assertions.assertNotNull(persistImageController.getExitModel());
        Assertions.assertNotNull(persistImageController.getErrorView());
        Assertions.assertNotNull(persistImageController.getSavingViewFXML());
    }

    @Test
    public void instance() {
        persistImageController = PersistImageController.getInstance();
        Assertions.assertNotNull(persistImageController);
        Assertions.assertNotNull(PersistImageController.instance);
        Assertions.assertNotNull(persistImageController.getPersistImageModel());
        Assertions.assertNotNull(persistImageController.getPubSubModel());
        Assertions.assertNotNull(persistImageController.getExitModel());
        Assertions.assertNotNull(persistImageController.getErrorView());
        Assertions.assertNotNull(persistImageController.getSavingViewFXML());

    }

    @Test
    public void checkSingleton() {
        PersistImageController persistImageController1 = PersistImageController.getInstance();
        PersistImageController persistImageController2 = PersistImageController.getInstance();
        Assertions.assertEquals(persistImageController1, persistImageController2);
    }

    @Test
    public void testUpdate() {
        DataView dataViewMock1 = Mockito.mock(DataView.class);
        DataView dataViewMock2 = Mockito.mock(DataView.class);
        persistImageController = PersistImageController.getInstance();
        persistImageController.initialize(Arrays.asList(dataViewMock1, dataViewMock2));

        persistImageController.update(EventType.SAVE_IMAGE);
        verify(dataViewMock1, times(1)).update(EventType.SAVE_IMAGE);
        verify(dataViewMock2, times(1)).update(EventType.SAVE_IMAGE);
    }

    @Test
    public void testRequestSaveBeforeOpen() {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        SavingViewFXML mockSavingView = Mockito.mock(SavingViewFXML.class);
        Component componentMock = Mockito.mock(Component.class);
        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class);
             MockedStatic<SavingViewFXML> savingViewStaticMock = Mockito.mockStatic(SavingViewFXML.class)) {
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            savingViewStaticMock.when(SavingViewFXML::getInstance).thenReturn(mockSavingView);
            persistImageController = PersistImageController.getInstance();

            Mockito.when(mockPersistImageModel.existCurrentFile()).thenReturn(true);
            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(false);
            persistImageController.requestSaveBeforeOpen(componentMock);
            verify(mockPersistImageModel, times(1)).existCurrentFile();
            verify(mockPersistImageModel, times(1)).isAlreadySave();
            verify(mockSavingView, times(1)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());

            Mockito.clearInvocations(mockPersistImageModel, mockSavingView);

            Mockito.when(mockPersistImageModel.existCurrentFile()).thenReturn(false);
            persistImageController.requestSaveBeforeOpen(componentMock);
            verify(mockPersistImageModel, times(1)).existCurrentFile();
            verify(mockPersistImageModel, times(0)).isAlreadySave();
            verify(mockSavingView, times(0)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());
            verify(mockSavingView, times(1)).getOpenFile(Mockito.any());
        }
    }

    @Test
    public void testRequestSaveBeforeOpenRecent() {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        SavingViewFXML mockSavingView = Mockito.mock(SavingViewFXML.class);
        Component componentMock = Mockito.mock(Component.class);
        Mockito.when(componentMock.getId()).thenReturn("");
        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class);
             MockedStatic<SavingViewFXML> savingViewStaticMock = Mockito.mockStatic(SavingViewFXML.class)) {
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            savingViewStaticMock.when(SavingViewFXML::getInstance).thenReturn(mockSavingView);
            persistImageController = PersistImageController.getInstance();

            Mockito.when(mockPersistImageModel.existCurrentFile()).thenReturn(true);
            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(false);
            persistImageController.requestSaveBeforeOpenRecent(componentMock);
            verify(mockSavingView, times(1)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());

            Mockito.clearInvocations(mockSavingView);

            Mockito.when(mockPersistImageModel.existCurrentFile()).thenReturn(false);
            persistImageController.requestSaveBeforeOpenRecent(componentMock);
            verify(mockSavingView, times(0)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());
        }
    }

    @Test
    public void testSaveImage() {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class)) {
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            persistImageController = PersistImageController.getInstance();

            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(false);
            persistImageController.saveImage(Mockito.mock(Component.class));
            verify(mockPersistImageModel, times(1)).writeImage();
            verify(mockPersistImageModel, times(1)).setAlreadySave(true);
        }
    }

    @Test
    public void testSaveImageAs() {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        SavingViewFXML mockSavingView = Mockito.mock(SavingViewFXML.class);
        File mockFile = Mockito.mock(File.class);

        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class);
             MockedStatic<SavingViewFXML> savingViewStaticMock = Mockito.mockStatic(SavingViewFXML.class)) {
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            savingViewStaticMock.when(SavingViewFXML::getInstance).thenReturn(mockSavingView);
            Mockito.when(mockSavingView.getSaveFile(Mockito.any())).thenReturn(mockFile);
            persistImageController = PersistImageController.getInstance();

            persistImageController.saveImageAs(Mockito.mock(Component.class));
            verify(mockPersistImageModel, times(1)).setNewSavingFile(mockFile);
            verify(mockPersistImageModel, times(1)).writeImage();
            verify(mockPersistImageModel, times(1)).setAlreadySave(true);
        }
    }

    @Test
    public void testLoadImage() throws Exception {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        SavingViewFXML mockSavingView = Mockito.mock(SavingViewFXML.class);
        ErrorViewPopUp mockErrorView = Mockito.mock(ErrorViewPopUp.class);
        File mockFile = Mockito.mock(File.class);

        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class);
             MockedStatic<SavingViewFXML> savingViewStaticMock = Mockito.mockStatic(SavingViewFXML.class);
             MockedStatic<ErrorViewPopUp> errorViewStaticMock = Mockito.mockStatic(ErrorViewPopUp.class)) {

            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            savingViewStaticMock.when(SavingViewFXML::getInstance).thenReturn(mockSavingView);
            errorViewStaticMock.when(ErrorViewPopUp::getInstance).thenReturn(mockErrorView);
            persistImageController = PersistImageController.getInstance();

            // Valid load
            Mockito.doNothing().when(mockPersistImageModel).loadImage(mockFile);
            Mockito.when(mockFile.exists()).thenReturn(true);
            Method loadImageMethod = PersistImageController.class.getDeclaredMethod("loadImage", File.class);
            loadImageMethod.setAccessible(true);
            loadImageMethod.invoke(persistImageController, mockFile);
            verify(mockPersistImageModel, times(1)).setNewSavingFile(mockFile);
            verify(mockPersistImageModel, times(1)).setAlreadySave(true);
            verify(mockPersistImageModel, times(1)).loadImage(mockFile);

            // Not valid load
            Mockito.doThrow(new IOException("File not readable")).when(mockPersistImageModel).loadImage(mockFile);
            loadImageMethod.invoke(persistImageController, mockFile);
            verify(mockPersistImageModel, times(1)).setNewSavingFile(null);
            verify(mockPersistImageModel, times(1)).setAlreadySave(false);
            verify(mockErrorView, times(1)).showPopUpError("IOException", "File not readable");
        }
    }


    @Test
    public void testRequestSaveBeforeClose() {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        SavingViewFXML mockSavingView = Mockito.mock(SavingViewFXML.class);
        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class);
             MockedStatic<SavingViewFXML> savingViewStaticMock = Mockito.mockStatic(SavingViewFXML.class)) {
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            savingViewStaticMock.when(SavingViewFXML::getInstance).thenReturn(mockSavingView);
            persistImageController = PersistImageController.getInstance();

            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(false);
            persistImageController.requestSaveBeforeClose(Mockito.mock(Component.class));
            verify(mockSavingView, times(1)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());

            Mockito.clearInvocations(mockSavingView);

            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(true);
            persistImageController.requestSaveBeforeClose(Mockito.mock(Component.class));
            verify(mockSavingView, times(0)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());
            verify(mockPersistImageModel, times(1)).closeImage();
        }
    }

    @Test
    public void testRequestSaveBeforeQuit() {
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        SavingViewFXML mockSavingView = Mockito.mock(SavingViewFXML.class);
        ExitModel mockExitModel = Mockito.mock(ExitModel.class);
        try (MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class);
             MockedStatic<SavingViewFXML> savingViewStaticMock = Mockito.mockStatic(SavingViewFXML.class);
             MockedStatic<ExitModel> exitModelStaticMock = Mockito.mockStatic(ExitModel.class)) {
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            savingViewStaticMock.when(SavingViewFXML::getInstance).thenReturn(mockSavingView);
            exitModelStaticMock.when(ExitModel::getInstance).thenReturn(mockExitModel);
            persistImageController = PersistImageController.getInstance();

            Mockito.when(mockPersistImageModel.existCurrentFile()).thenReturn(true);
            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(false);
            persistImageController.requestSaveBeforeQuit(Mockito.mock(Component.class));
            verify(mockSavingView, times(1)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());

            Mockito.clearInvocations(mockSavingView, mockExitModel);

            Mockito.when(mockExitModel.closeApplication()).thenReturn(Mockito.mock(Runnable.class));
            Mockito.when(mockPersistImageModel.isAlreadySave()).thenReturn(true);
            persistImageController.requestSaveBeforeQuit(Mockito.mock(Component.class));
            verify(mockSavingView, times(0)).showSaveConfirmationPopup(Mockito.any(), Mockito.any());
            verify(mockExitModel, times(1)).closeApplication();
        }
    }
}