package ch.supsi.imageEditor.backend.dataaccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

public class AbstractDataAccessTest {
    protected final String userHomeDirectory = System.getProperty("user.home");
    protected final String preferencesDirectory = ".userpreferences";
    protected final String backupPreferencesDirectory = ".userpreferences-backup";
    protected boolean originalPreferencesDirectoryExists;
    protected Path preferencesPath;
    protected Path backupPreferencesPath;


    protected void setUp() {
        preferencesPath = Paths.get(userHomeDirectory, preferencesDirectory);
        backupPreferencesPath = Paths.get(userHomeDirectory, backupPreferencesDirectory);
        this.originalPreferencesDirectoryExists = false;
        try {
            if (Files.exists(preferencesPath)) {
                originalPreferencesDirectoryExists = true;
                this.copyDirectory(preferencesPath, backupPreferencesPath);
                this.deleteDirectory(preferencesPath);
            }
        } catch (IOException e) {
            Assertions.fail(e.getMessage());
        }
    }


    protected void tearDown() {
        try {
            if (originalPreferencesDirectoryExists) {
                this.copyDirectory(backupPreferencesPath, preferencesPath);
                this.deleteDirectory(backupPreferencesPath);
            } else {
                if(Files.exists(preferencesPath))
                    this.deleteDirectory(preferencesPath);
            }
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    private void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source).forEach(path -> {
            try {
                Path targetPath = target.resolve(source.relativize(path));
                if (Files.isDirectory(path))
                    Files.createDirectories(targetPath);
                else
                    Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ignored) {
                ;
            }
        });
    }

    private void deleteDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException ignore) {
                        ;
                    }
                });
    }
}
