package org.koenighotze.chapter3;

import static javafx.scene.paint.Color.WHITE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.koenighotze.chapter3.ImageHelper.loadImage;
import static org.koenighotze.chapter3.ImageHelper.transform;

import java.util.stream.*;

import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.*;

/**
 * @author dschmitz
 */
public class Ex3_5Test {

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
}
