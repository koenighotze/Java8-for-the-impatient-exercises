package org.koenighotze.chapter3;

import javafx.scene.paint.*;

/**
 * Created by dschmitz on 30.03.15.
 */
@FunctionalInterface
public interface ColorTransformer {
    Color transform(int x, int y, Color colorAtXY);
}
