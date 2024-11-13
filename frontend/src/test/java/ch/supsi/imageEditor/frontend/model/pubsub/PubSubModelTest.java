package ch.supsi.imageEditor.frontend.model.pubsub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PubSubModelTest {
    @BeforeEach
    public void beforeEach() {
        PubSubModel.instance = null;
    }

    @Test
    public void constructor() {
        PubSubModel pubSubModel = new PubSubModel();
        Assertions.assertNotNull(pubSubModel);
        Assertions.assertNotNull(pubSubModel.getNotificationService());
    }

    @Test
    public void instance() {
        PubSubModel pubSubModel = PubSubModel.getInstance();
        Assertions.assertNotNull(pubSubModel);
        Assertions.assertNotNull(PubSubModel.instance);
        Assertions.assertNotNull(pubSubModel.getNotificationService());
    }

    @Test
    public void checkSingleton() {
        PubSubModel pubSubModel1 = PubSubModel.getInstance();
        PubSubModel pubSubModel2 = PubSubModel.getInstance();
        Assertions.assertEquals(pubSubModel1, pubSubModel2);
    }
}