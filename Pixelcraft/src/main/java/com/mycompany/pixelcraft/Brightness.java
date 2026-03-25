/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;

/**
 * Increases the brightness of the image by adding a fixed offset to each
 * colour channel (red, green, and blue). Values are clamped to [0, 255] so
 * that no channel overflows. The alpha channel is left unchanged.
 *
 * A positive {@value #DELTA} brightens the image; a negative value would darken it.
 *
 * Implementation strategy: purely recursive, no loops used anywhere.
 * Rows are traversed via {@link #processRow}, which recurses on {@code y + 1}
 * until all rows are processed. Within each row, pixels are traversed via
 * {@link #processPixel}, which recurses on {@code x + 1} until the row ends.
 * This two level approach keeps the maximum stack depth at
 * {@code max(width, height)} rather than {@code width × height}, preventing
 * stack overflow on real world images.
 *
 * @author Koosha Shamdani
 */
public class Brightness extends RecursiveConverter {

    private static final int DELTA = 50;

    /**
     * Transforms a single pixel by adding {@value #DELTA} to each of the
     * red, green, and blue channels. Each channel is clamped to [0, 255]
     * to prevent overflow. The alpha channel is preserved unchanged.
     *
     * @param pixel the source pixel as a packed ARGB {@code int}
     * @return a new packed ARGB {@code int} with brightened RGB channels
     */
    @Override
    protected int transformPixel(int pixel) {
        // Unpack the raw pixel into individual ARGB components
        ARGB p = new ARGB(pixel);
         // Add DELTA to each colour channel, clamping to keep values in [0, 255]
        return new ARGB(
            p.alpha,
            ARGB.clamp(p.red + DELTA),
            ARGB.clamp(p.green + DELTA),
            ARGB.clamp(p.blue + DELTA)
        ).toInt();
    }
}
