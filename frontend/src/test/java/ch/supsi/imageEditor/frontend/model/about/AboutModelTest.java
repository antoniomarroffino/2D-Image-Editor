package ch.supsi.imageEditor.frontend.model.about;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class AboutModelTest {
    private AboutModel aboutModel;

    @BeforeEach
    public void beforeEach() {
        AboutModel.instance = null;
        this.aboutModel = AboutModel.getInstance();
    }

    @Test
    public void constructor() {
        this.aboutModel = new AboutModel();
        Assertions.assertNotNull(this.aboutModel);
        Assertions.assertNotNull(this.aboutModel.getBuildProperties());
    }

    @Test
    public void instance() {
        this.aboutModel = AboutModel.getInstance();
        Assertions.assertNotNull(this.aboutModel);
        Assertions.assertNotNull(AboutModel.instance);
        Assertions.assertNotNull(this.aboutModel.getBuildProperties());
    }

    @Test
    public void checkSingleton() {
        AboutModel aboutModel1 = AboutModel.getInstance();
        AboutModel aboutModel2 = AboutModel.getInstance();
        Assertions.assertEquals(aboutModel1, aboutModel2);
    }


    @Test
    void getVersion() {
        Properties properties = this.aboutModel.getBuildProperties();
        Assertions.assertEquals(": " + properties.getProperty("build.version"), this.aboutModel.getVersion());
    }

    @Test
    void getProjectName() {
        Properties properties = this.aboutModel.getBuildProperties();
        Assertions.assertEquals(": " + properties.getProperty("build.name"), this.aboutModel.getProjectName());
    }

    @Test
    void getDevelopersName() {
        Properties properties = this.aboutModel.getBuildProperties();
        Assertions.assertEquals(": " + properties.getProperty("build.devs"), this.aboutModel.getDevelopersName());
    }

    @Test
    void getBuiltDate() {
        Properties properties = this.aboutModel.getBuildProperties();
        Assertions.assertEquals(": " + properties.getProperty("build.timestamp"), this.aboutModel.getBuiltDate());
    }

    @Test
    void getTitle() {
        Assertions.assertEquals("2D Image Editor - About", this.aboutModel.getTitle());
    }

    @Test
    void getHeaderText() {
        Assertions.assertEquals(": " + System.lineSeparator() + "2D Image Editor", this.aboutModel.getHeaderText());
    }
}