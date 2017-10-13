package org.koenighotze.chapter3;

import static java.util.stream.IntStream.range;
import static javafx.embed.swing.SwingFXUtils.fromFXImage;

import java.io.*;
import java.util.stream.*;
import javax.imageio.*;

import javafx.scene.image.*;
import javafx.scene.paint.*;

/**
 * @author dschmitz
 */
public class ImageHelper {
    public static void storeImage(Image image) throws IOException {
        java.awt.image.RenderedImage bufferedImage = fromFXImage(image, null);
        File out = File.createTempFile("test", ".png");
        ImageIO.write(bufferedImage, "png", out);
        System.out.println("Result " + out.getAbsolutePath());
    }

    public static Image loadImage() {
        return new Image(ImageHelper.class.getResourceAsStream("file.png"));
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

    public static void assertImage(Image result, PixelComparator comparator) {
        PixelReader reader = result.getPixelReader();
        range(0, (int) result.getWidth()).forEach(x -> range(0, (int) result.getHeight()).forEach(y -> comparator.assertPixel(reader, x, y)));
    }
}
