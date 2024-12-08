package ch.supsi.imageEditor.frontend.model;

public class AppModel extends AbstractModel {
    protected static AppModel instance = null;

    protected AppModel() {
    }

    public static AppModel getInstance() {
        return instance == null ? instance = new AppModel() : instance;
    }
}
