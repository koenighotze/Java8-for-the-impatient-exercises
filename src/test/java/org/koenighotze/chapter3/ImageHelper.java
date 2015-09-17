package org.koenighotze.chapter3;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;

/**
 * @author dschmitz
 */
public class ImageHelper {
    public static void storeImage(Image newImage) throws IOException {
        java.awt.image.RenderedImage bufferedImage = fromFXImage(newImage, null);
        File out = File.createTempFile("test", ".png");
        ImageIO.write(bufferedImage, "png", out);
        System.out.println("Image should be white " + out.getAbsolutePath());
    }

    public static Image loadImage() {
        return new Image(Ex3_5Test.class.getResourceAsStream("file.png"));
    }

    public static Image transform(Image in, ColorTransformer colorTransformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();

        WritableImage out = new WritableImage(width, height);

        IntStream.range(0, width)
                .forEach(w -> IntStream.range(0, height)
                        .forEach(h -> {
                            Color newColor = colorTransformer.transform(w, h, in.getPixelReader().getColor(w, h));
                            if (null == newColor) {
                                newColor = in.getPixelReader().getColor(w, h);
                            }
                            out.getPixelWriter().setColor(w, h, newColor);
                        }));
        return out;
    }
}
