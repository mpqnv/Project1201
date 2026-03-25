/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;
/**
 * Applies a warm sepia tone effect to the image, giving it an aged,
 * brownish appearance similar to old photographs.
 *
 * The transformation uses the standard sepia tone matrix:
 * 
 *   outR = clamp(0.393*R + 0.769*G + 0.189*B)
 *   outG = clamp(0.349*R + 0.686*G + 0.168*B)
 *   outB = clamp(0.272*R + 0.534*G + 0.131*B)
 * 
 * The alpha channel is preserved unchanged.
 * 
 * Implementation strategy: iterative (nested for loops over every pixel).
 * @author Alper Diker
 */
public class Sepia extends Converter {
    /**
     * Applies a sepia tone filter to the given image.
     *
     * @param image the source image
     * @return a new sepia-toned image of the same dimensions
     */
     @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create the new image to return
        BufferedImage sepiaImage = new BufferedImage(width, height, image.getType());

        // Go through every pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB p = new ARGB(image.getRGB(x, y));

                // Get individual color channels and apply the sepia transformation formulas
                int newRed   = ARGB.clamp((int)(0.393 * p.red + 0.769 * p.green + 0.189 * p.blue));
                int newGreen = ARGB.clamp((int)(0.349 * p.red + 0.686 * p.green + 0.168 * p.blue));
                int newBlue  = ARGB.clamp((int)(0.272 * p.red + 0.534 * p.green + 0.131 * p.blue));

                // Set the new color to the pixel
                sepiaImage.setRGB(x, y, new ARGB(p.alpha, newRed, newGreen, newBlue).toInt());
            }
        }
        //Return the final image
        return sepiaImage;
    }
}
