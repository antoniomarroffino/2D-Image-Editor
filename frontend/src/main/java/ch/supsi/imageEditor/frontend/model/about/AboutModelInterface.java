package ch.supsi.imageEditor.frontend.model.about;

import java.io.InputStream;

public interface AboutModelInterface {
    String getVersion();

    String getProjectName();

    String getDevelopersName();

    String getBuiltDate();

    String getTitle();

    String getHeaderText();

    String getContextText();

    InputStream getLogoInputStream();
}
