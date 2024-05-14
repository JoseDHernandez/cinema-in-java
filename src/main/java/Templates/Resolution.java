package Templates;

import java.awt.Dimension;

/**
 *
 * @author Jose
 */
public interface Resolution {

    /**
     *
     * Ancho predeterminado en píxeles para las escenas.
     */
    final int WIDTH_PX = 1336;
    /**
     *
     * Alto predeterminado en píxeles para las escenas.
     */
    final int HEIGHT_PX = 768;
    /**
     *
     * Dimensión predeterminada para la resolución de las escenas.
     */
    final Dimension DEF_RESOLUTION = new Dimension(WIDTH_PX, HEIGHT_PX);
    final int H = 673;
    final Dimension BLOCK_RESOLUTION = new Dimension(WIDTH_PX, H);
}
