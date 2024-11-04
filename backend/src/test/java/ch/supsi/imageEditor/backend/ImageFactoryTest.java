package ch.supsi.imageEditor.backend;

import ch.supsi.imageEditor.backend.business.images.ImageFactory;
import ch.supsi.imageEditor.backend.exception.FormatNotSupportedException;
import ch.supsi.imageEditor.backend.exception.ImageHeaderUncorrectException;

import java.io.IOException;

public class ImageFactoryTest extends ImageFactory {
    public ImageFactoryTest() {
        super();
    }

    @Override
    public void readImage(String filePath) throws FormatNotSupportedException, IOException, ImageHeaderUncorrectException {
        System.out.println("ENTRO NEL TEST");
        String extension = this.getFileExtension(filePath).toUpperCase();
        this.currentImageReader = this.imageReaders.get(extension);
        if (this.currentImageReader == null)
            throw new FormatNotSupportedException("Format " + extension + " is not supported");
        this.currentImageReader.read(filePath);
        this.currentImage = this.currentImageReader.getImage();
    }
}