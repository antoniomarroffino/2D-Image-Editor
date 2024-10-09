package ch.supsi.imageEditor.frontend.model;

public class AppModel extends AbstractModel {
    private static AppModel instance = null;

    private AppModel() {}

    public static AppModel getInstance() {
        return instance == null ? instance = new AppModel() : instance;
    }
}
