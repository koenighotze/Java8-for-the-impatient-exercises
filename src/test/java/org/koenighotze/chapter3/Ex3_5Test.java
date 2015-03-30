package org.koenighotze.chapter3;

import javafx.embed.swing.SwingFXUtils;
import  javafx.scene.image.*;
import javafx.scene.paint.Color;
import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static javafx.embed.swing.SwingFXUtils.*;
import static javafx.scene.paint.Color.WHITE;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by dschmitz on 30.03.15.
 */
public class Ex3_5Test {
    public static Image transform(Image in, ColorTransformer colorTransformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();

        WritableImage out = new WritableImage(width, height);

        IntStream.range(0, width)
                 .forEach(w -> IntStream.range(0, height)
                     .forEach(h -> {
                         Color newColor = colorTransformer.transform(w, h, in.getPixelReader().getColor(w, h));
                         out.getPixelWriter().setColor(w, h, newColor);
                     }));
        return out;
    }

    @Test
    public void testApplyColorTransformer() throws Exception {
        Image image = loadImage();

        Image newImage = transform(image, (x, y, c) -> WHITE);

        assertThat("Image must not be null", newImage, is(not(nullValue())));

        IntStream.range(0, (int) newImage.getWidth())
            .forEach(x ->
                IntStream.range(0, (int) newImage.getHeight())
                    .forEach(
                        y -> assertThat("All Pixels must be WHITE (" + x + ", " +y + ")",
                            newImage.getPixelReader().getColor(x, y), is(equalTo(WHITE)))));

        // uncomment to write file to disk and see for yourself
        // storeImage(newImage);
    }

    @Test
    public void testGenerateBorder() throws Exception {
        Image image = loadImage();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        Image newImage = transform(image,
            (x, y, c) -> {
                if (x < 10 || x > width - 10) {
                    return Color.GREEN;
                }

                if (y < 10 || y > height - 10) {
                    return Color.GREEN;
                }

                return c;
            });

        assertThat("Image must not be null", newImage, is(not(nullValue())));
//        storeImage(newImage);
    }

    private void storeImage(Image newImage) throws IOException {
        java.awt.image.RenderedImage bufferedImage = fromFXImage(newImage, null);
        File out = File.createTempFile("test", ".png");
        ImageIO.write(bufferedImage, "png", out);
        System.out.println("Image should be white " + out.getAbsolutePath());
    }

    private Image loadImage() {
        return new Image(Ex3_5Test.class.getResourceAsStream("file.png"));
    }

}
