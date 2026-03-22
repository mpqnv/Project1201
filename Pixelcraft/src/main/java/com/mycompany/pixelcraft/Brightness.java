/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;

/**
 ** Increases the brightness of the image by adding a fixed offset to each
 * colour channel (red, green, and blue). Values are clamped to [0, 255] so
 * that no channel overflows. The alpha channel is left unchanged.
 *
 * A positive {@code DELTA} brightens the image; a negative value would darken it.
 *
 * Implementation strategy: purely recursive. No loops are
 * used anywhere. Pixel traversal is performed via tail recursion in row major
 * order, mirroring the approach used in the Invert converter but with a
 * completely different per-pixel operation.
 * @author Koosha Shamdani
 */
public class Brightness extends Converter{
    
    private static final int DELTA = 50;
    /**
     * Brightens the image by {@value #DELTA} units per channel.
     *
     * @param image the source image
     * @return a new brightened image of the same dimensions
     */
    @Override
    protected BufferedImage process(BufferedImage image){
        int width = image.getWidth(); 
        int height = image.getHeight(); 
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        //Start the recursive traversal at the top left pixel
        
        processPixel(image, result, 0, 0, width, height); 
        return result; 
    }

    /**
     * Recursively processes every pixel in row major order, adding {@value #DELTA}
     * to each colour channel and clamping the result.
     *
     * @param src    the source image (read only)
     * @param dst    the destination image (written to)
     * @param x      current column index
     * @param y      current row index
     * @param width  image width
     * @param height image height
     */
    
private void processPixel(BufferedImage src, BufferedImage dst, int x, int y, int width, int height) {
    
    // Base case
    if (y >= height) return;

    ARGB p = new ARGB(src.getRGB(x, y));
    ARGB bright = new ARGB(
        p.alpha,
        ARGB.clamp(p.red   + DELTA),
        ARGB.clamp(p.green + DELTA),
        ARGB.clamp(p.blue  + DELTA)
    );
    dst.setRGB(x, y, bright.toInt());

    // Recurse to the next pixel
    if (x + 1 < width) processPixel(src, dst, x + 1, y, width, height);
    else processPixel(src, dst, 0, y + 1, width, height);
}
    
}
