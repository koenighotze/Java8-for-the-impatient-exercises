package org.koenighotze.chapter3;

import static java.util.stream.IntStream.range;
import static javafx.scene.paint.Color.AQUA;
import static javafx.scene.paint.Color.GREY;
import static javafx.scene.paint.Color.WHITE;
import static org.junit.Assert.assertEquals;
import static org.koenighotze.chapter3.ImageHelper.loadImage;
import static org.koenighotze.chapter3.ImageHelper.storeImage;
import static org.koenighotze.chapter3.ImageHelper.transform;

import java.io.*;
import java.util.function.*;

import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.*;

/**
 * @author dschmitz
 */
public class Ex3_11Test {

    public static ColorTransformer compose(ColorTransformer first, ColorTransformer second) {
        return (x, y, c) -> first.transform(x, y, second.transform(x, y, c));
    }

    public static ColorTransformer simpleTransformer(UnaryOperator<Color> op) {
        return (x, y, z) -> op.apply(z);
    }

    @Test
    public void compose() {
        ColorTransformer colorTransformer = simpleTransformer(t -> AQUA);
        ColorTransformer flipper = (x, y, colorAtXY) -> {
            if (y % 2 == 0) {
                return WHITE;
            } else {
                return colorAtXY;
            }
        };
        Image transform = transform(loadImage(), compose(flipper, colorTransformer));
        PixelReader reader = transform.getPixelReader();

        range(0, (int) transform.getWidth()).forEach(x -> range(0, (int) transform.getHeight()).filter(y -> y % 2 == 0).forEach(y -> assertEquals(
            reader.getColor(x, y), WHITE)));
    }

    @Test
    public void create_simple_transformer() {
        ColorTransformer colorTransformer = simpleTransformer(t -> AQUA);

        Image transform = transform(loadImage(), colorTransformer);
        PixelReader reader = transform.getPixelReader();

        range(0, (int) transform.getWidth()).forEach(x -> range(0, (int) transform.getHeight()).forEach(y -> assertEquals(
            "Color is " + reader.getColor(x, y), AQUA, reader.getColor(x, y))));
    }

    @Test
    public void apply_grey_border() throws IOException {
        Image original = loadImage();
        int w = (int) original.getWidth();
        int h = (int) original.getHeight();

        ColorTransformer brightened = simpleTransformer(Color::brighter);

        ColorTransformer border = (x, y, c) -> {
            if ((x < 10 || x > w - 10) ||  (y < 10 || y > h -10)) {
                return GREY;
            }
            return c;
        };

        Image transform = transform(loadImage(), compose(brightened, border));
        storeImage(transform);
    }
}
