package ch.supsi.imageEditor.frontend.model.about;

import ch.supsi.imageEditor.frontend.model.AbstractModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class AboutModel extends AbstractModel implements AboutModelInterface {
    private final static String PATH_BUILD_PROPERTIES = "/build.properties";
    private final static String PATH_LOGO_APP_IMAGE = "/images/logoApp.png";
    protected static AboutModel instance = null;
    private final Properties buildProperties;

    protected AboutModel() {
        this.buildProperties = loadBuildProperties();
    }

    public static AboutModel getInstance() {
        return instance == null ? instance = new AboutModel() : instance;
    }

    private Properties loadBuildProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(PATH_BUILD_PROPERTIES);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    Properties getBuildProperties() {
        return this.buildProperties;
    }

    @Override
    public String getVersion() {
        return ": " + this.buildProperties.getProperty("build.version");
    }

    @Override
    public String getProjectName() {
        return ": " + this.buildProperties.getProperty("build.name");
    }

    @Override
    public String getDevelopersName() {
        return ": " + this.buildProperties.getProperty("build.devs");
    }

    @Override
    public String getBuiltDate() {
        return ": " + this.buildProperties.getProperty("build.timestamp");
    }

    @Override
    public String getTitle() {
        return "2D Image Editor - About";
    }

    @Override
    public String getHeaderText() {
        return ": " + System.lineSeparator() + "2D Image Editor";
    }

    @Override
    public InputStream getLogoInputStream() {
        return Objects.requireNonNull(getClass().getResourceAsStream(PATH_LOGO_APP_IMAGE));
    }
}
