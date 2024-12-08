package ch.supsi.imageEditor.frontend.controller.pipeline;

import ch.supsi.imageEditor.backend.application.observer.EventType;
import ch.supsi.imageEditor.frontend.adapter.Component;
import ch.supsi.imageEditor.frontend.model.persist.PersistImageModel;
import ch.supsi.imageEditor.frontend.model.pipeline.PipelineModel;
import ch.supsi.imageEditor.frontend.view.fxml.DataView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PipelineControllerTest {
    private PipelineController pipelineController;

    @BeforeEach
    public void beforeEach() {
        PipelineController.instance = null;
    }

    @Test
    public void constructor() {
        pipelineController = new PipelineController();
        Assertions.assertNotNull(pipelineController);
        Assertions.assertNotNull(pipelineController.getPipelineModel());
        Assertions.assertNotNull(pipelineController.getPubSubModel());
        Assertions.assertNotNull(pipelineController.getPersistModel());
    }

    @Test
    public void instance() {
        pipelineController = PipelineController.getInstance();
        Assertions.assertNotNull(pipelineController);
        Assertions.assertNotNull(PipelineController.instance);
        Assertions.assertNotNull(pipelineController.getPipelineModel());
        Assertions.assertNotNull(pipelineController.getPubSubModel());
        Assertions.assertNotNull(pipelineController.getPersistModel());
    }

    @Test
    public void checkSingleton() {
        PipelineController pipelineController1 = PipelineController.getInstance();
        PipelineController pipelineController2 = PipelineController.getInstance();
        Assertions.assertEquals(pipelineController1, pipelineController2);
    }

    @Test
    public void testDeletePipeline() {
        PipelineModel mockPipelineModel = Mockito.mock(PipelineModel.class);
        Component componentMock = Mockito.mock(Component.class);
        try (MockedStatic<PipelineModel> pipelineModelStaticMock = Mockito.mockStatic(PipelineModel.class)) {
            pipelineModelStaticMock.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            pipelineController = PipelineController.getInstance();

            pipelineController.deletePipeline(componentMock);
            verify(mockPipelineModel, times(1)).deletePipeline();
        }
    }

    @Test
    public void testRunPipeline() {
        PipelineModel mockPipelineModel = Mockito.mock(PipelineModel.class);
        PersistImageModel mockPersistImageModel = Mockito.mock(PersistImageModel.class);
        Component componentMock = Mockito.mock(Component.class);
        try (MockedStatic<PipelineModel> pipelineModelStaticMock = Mockito.mockStatic(PipelineModel.class);
             MockedStatic<PersistImageModel> persistImageModelStaticMock = Mockito.mockStatic(PersistImageModel.class)) {
            pipelineModelStaticMock.when(PipelineModel::getInstance).thenReturn(mockPipelineModel);
            persistImageModelStaticMock.when(PersistImageModel::getInstance).thenReturn(mockPersistImageModel);
            pipelineController = PipelineController.getInstance();

            pipelineController.runPipeline(componentMock);
            verify(mockPipelineModel, times(1)).runPipeline();
            verify(mockPersistImageModel, times(1)).setAlreadySave(false);
        }
    }

    @Test
    public void testUpdate() {
        DataView dataViewMock1 = Mockito.mock(DataView.class);
        DataView dataViewMock2 = Mockito.mock(DataView.class);
        pipelineController = PipelineController.getInstance();
        pipelineController.initialize(Arrays.asList(dataViewMock1, dataViewMock2));

        pipelineController.update(EventType.ADDED_OPERATION);
        verify(dataViewMock1, times(1)).update(EventType.ADDED_OPERATION);
        verify(dataViewMock2, times(1)).update(EventType.ADDED_OPERATION);
    }
}