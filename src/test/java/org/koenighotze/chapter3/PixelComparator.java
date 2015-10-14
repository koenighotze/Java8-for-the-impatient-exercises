package org.koenighotze.chapter3;

import javafx.scene.image.PixelReader;

/**
 * @author dschmitz
 */
@FunctionalInterface
public interface PixelComparator {
    void assertPixel(PixelReader reader, int x, int y);
}
