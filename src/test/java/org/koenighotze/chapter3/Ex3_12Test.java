package org.koenighotze.chapter3;

import static java.util.Objects.requireNonNull;
import static javafx.scene.paint.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.koenighotze.chapter3.Ex3_11Test.simpleTransformer;
import static org.koenighotze.chapter3.ImageHelper.assertImage;
import static org.koenighotze.chapter3.ImageHelper.loadImage;
import static org.koenighotze.chapter3.ImageHelper.storeImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;

/**
 * @author dschmitz
 */
public class Ex3_12Test {

    public static class LatentImage {
        private Image original;

        private final List<ColorTransformer> operations = new ArrayList<>();

        private LatentImage(Image image) {
            original = image;
        }

        public static LatentImage from(Image image) {
            return new LatentImage(requireNonNull(image));
        }

        public LatentImage transform(UnaryOperator<Color> op) {
            operations.add(simpleTransformer(requireNonNull(op)));
            return this;
        }

        public Image toImage() {
            int width = (int) original.getWidth();
            int height = (int) original.getHeight();

            WritableImage out = new WritableImage(width, height);

            IntStream.range(0, width)
                .forEach(x -> IntStream.range(0, height).forEach(y -> {
                Color newColor = original.getPixelReader().getColor(x, y);
                for (ColorTransformer op : operations) {
                    newColor = op.transform(x, y, newColor);
                }

                out.getPixelWriter().setColor(x, y, newColor);
            }));
            return out;
        }

        public LatentImage transform(ColorTransformer transformer) {
            operations.add(transformer);
            return this;
        }
    }

    @Test
    public void latent_image_identity() throws IOException {
        Image original = loadImage();

        Image result = LatentImage.from(original).toImage();

        PixelReader originalReader = original.getPixelReader();
        assertImage(result, (reader, x, y) -> assertEquals(originalReader.getColor(x, y), reader.getColor(x, y)));

        storeImage(result);
    }

    @Test
    public void build_simple_image() throws IOException {
        Image original = loadImage();

        Image result = LatentImage.from(original).transform(Color::brighter).transform(Color::saturate).transform(c -> c.deriveColor(0.3d, 0.7d, 3d,
            1d)).toImage();

        PixelReader originalReader = original.getPixelReader();
        assertImage(result, (reader, x, y) -> assertNotEquals(originalReader.getColor(x, y), reader.getColor(x, y)));

        storeImage(result);
    }

    @Test
    public void latent_image_supports_colortransformer() throws IOException {
        ColorTransformer flipper = (x, y, colorAtXY) -> {
            if (y % 2 == 0) {
                return WHITE;
            } else {
                return colorAtXY;
            }
        };

        Image original = loadImage();

        Image result = LatentImage.from(original).transform(Color::brighter).transform(Color::saturate).transform(flipper).toImage();

        PixelReader originalReader = original.getPixelReader();
        assertImage(result, (reader, x, y) -> assertNotEquals(originalReader.getColor(x, y), reader.getColor(x, y)));

        storeImage(result);

    }
}
